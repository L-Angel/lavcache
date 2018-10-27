package com.langel.lavcache.invoker;

import com.langel.lavcache.action.Action;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.cache.view.NullCacheValueView;
import com.langel.lavcache.concurrent.LavExecutorServiceFactory;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.piece.Piece;
import com.langel.lavcache.piece.PieceHolder;
import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.util.KeyGenerator;
import com.langel.lavcache.util.PieceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:54
 **/
public class PieceInvoker implements Invoker {

    private final Logger LOGGER = LoggerFactory.getLogger(Invoker.class);

    private final KeyGenerator keyGenerator = new KeyGenerator();

    @Override
    public Object invoke(Invocation invocation) throws Throwable {
        Method m = invocation.method();
        PieceHolder holder = PieceHolder.Builder.build(m);

        String key = keyGenerator.generate(holder, invocation.args());
        Sector sector = holder.sector();

        // rejudge piece is in sector or not
        String name = PieceUtils.name(holder);
        if (!sector.containsPiece(name)) {
            sector.addPiece(name, PieceUtils.toPiece(holder));
        }

        // used to reload cache
        loadAsync(sector, sector.piece(PieceUtils.name(holder)), key, invocation);
        CacheValueView valueView = sector.getRaw(key, holder);
        // get null val from native cache
        if (valueView instanceof NullCacheValueView) {

            // invoke origin method and let return valus  is cached
            Object r = invocation.joinpoint().proceed();
            sector.putRaw(key, r, holder);
            // broadcast event which the cache data action is compeleted.
            refreshedEvent(holder, holder.after(), key, r);
            return r;
        }
        return valueView.value();
    }

    /**
     * reload cache with sync
     *
     * @param sector
     * @param piece
     * @param key
     * @param iv
     */
    private void loadSync(Sector sector, Piece piece, String key, Invocation iv) {
        loadCache(sector, piece, key, iv, 1);
    }

    /**
     * reload cache with async
     *
     * @param sector
     * @param piece
     * @param key
     * @param iv
     */
    private void loadAsync(Sector sector, Piece piece, String key, Invocation iv) {
        boolean ar = sector.cache().config().autoreload();
        boolean nr = piece.needReload();
        if (ar && nr) {
            LavExecutorServiceFactory.executorService().submit(() -> {
                loadCache(sector, piece, key, iv, 1);
            });
        }
    }

    /**
     * reload cache
     *
     * @param sector sector
     * @param piece  piece holder
     * @param key
     * @param iv
     * @param retry  retry times
     * @return
     */
    private boolean loadCache(Sector sector, Piece piece, String key, Invocation iv, int retry) {
        try {
            // reset reload flag
            piece.needReload(false);
            Object r = new Object();
            while ((retry -= 1) >= 0 && r != null) {
                r = iv.joinpoint().proceed();
            }
            if (r != null) {
                sector.putRaw(key, r, piece.holder());
                LOGGER.warn("[ LavCache ] : Reload. Piece {} is reloaded.", key);
                return true;
            }
        } catch (Throwable throwable) {
            LOGGER.error("[ LavCache ] : Reload. ", throwable);
        }
        return false;
    }

    /**
     * broadcast event when cache load action is compelted
     *
     * @param holder
     * @param actions
     * @param key
     * @param val
     */
    private void refreshedEvent(PieceHolder holder,
                                Class<? extends Action>[] actions,
                                String key,
                                Object val) {
        Action action = null;
        for (Class<? extends Action> ac : actions) {
            action = SectorInjector.getInstance(ac);
            if (action == null) {
                continue;
            }
            action.call(holder, key, val);
            LOGGER.info("[ Action ] : {}, [ Event ] : {}", action.getClass().getName(), "Refreshed");
        }
    }
}
