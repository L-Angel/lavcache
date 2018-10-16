package com.langel.lavcache.piece;

import com.google.inject.Singleton;
import com.langel.lavcache.Container;
import com.langel.lavcache.concurrent.LavExecutorServiceFactory;
import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.util.KeyGenerator;
import com.langel.lavcache.util.PieceUtils;
import com.langel.lavcache.util.SectorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/27 下午8:46
 **/
public class PieceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PieceLoader.class);

    private AtomicInteger preLoadCount = new AtomicInteger(0);

    private final KeyGenerator keyGenerator = new KeyGenerator();

    public void loadPieces(Class clazz) {
        if (!SectorUtils.isSector(clazz)) {
            LOGGER.warn("[ LavCache ] : {} is not a sector.", clazz.getName());
            return;
        }
        Method[] methods = clazz.getDeclaredMethods();

        PieceHolder holder;
        Piece piece;
        for (Method m : methods) {
            if (!PieceUtils.isPiece(m)) {
                continue;
            }
            holder = PieceHolder.Builder.build(m);
            piece = PieceUtils.toPiece(holder);
            if (!holder.sector().containsPiece(piece.name())) {
                holder.sector().addPiece(piece.name(), piece);
                if (holder.option().preload()) {
                    preLoadCount.getAndIncrement();
                }
            }
        }
    }

    public boolean preload() {
        CountDownLatch latch = new CountDownLatch(preLoadCount.get());

        Map<String, Sector> sectorMap = Container.INSTANCE.sectorMap();
        for (Sector sector : sectorMap.values()) {
            Map<String, Piece> pieceMap = sector.pieceMap();
            for (Piece piece : pieceMap.values()) {
                PieceHolder holder = piece.holder();
                if (holder.option().preload()) {
                    loadCacheAsync(sector, holder, latch);
                }
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error("[ LavCache ] : Preload. MultiThreadException.", e);
            return false;
        }
        return true;
    }

    private void loadCacheAsync(Sector sector, PieceHolder holder, CountDownLatch latch) {
        LavExecutorServiceFactory.executorService().submit(() -> {
            Method m = holder.method();
            try {
                Object r = m.invoke(holder.target(), new Object[m.getParameterCount()]);
                if (null != r) {
                    latch.countDown();
                } else {
                    LOGGER.error("[ LavCache ] : Preload. NullArgumentException.");
                }
            } catch (Exception e) {
                LOGGER.error("[ LavCache ] : Preload. System Exception.", e);
            }
        });
    }

}
