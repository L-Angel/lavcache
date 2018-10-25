package com.langel.lavcache.sector;

import com.langel.lavcache.cache.CacheWrapper;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.cache.view.DefaultCacheValueView;
import com.langel.lavcache.cache.view.NullCacheValueView;
import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.Piece;
import com.langel.lavcache.piece.PieceHolder;
import com.langel.lavcache.provider.CacheProvider;
import com.langel.lavcache.provider.Provider;
import com.langel.lavcache.sector.record.Stats;
import com.langel.lavcache.sector.record.StatsOp;
import com.langel.lavcache.sector.record.StatsOpImpl;
import com.langel.lavcache.util.ExpireUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public class SectorImpl implements Sector {

    private final Logger LOGGER = LoggerFactory.getLogger(Sector.class);

    private final String name;

    private final CacheWrapper cache;

    private final Provider<CacheWrapper> provider;

    private final ConcurrentHashMap<String, Piece> meContainer = new ConcurrentHashMap<>();

    private final StatsOp statsOp;

    private final boolean record;

    /**
     * expire at .ms
     */
    private long expireAt;

    public SectorImpl(String name, Map<String, Piece> pieceMap) {
        assert name != null;
        assert pieceMap != null;

        this.name = name;
        this.provider = new CacheProvider();
        this.cache = this.provider.get(this.name);

        this.meContainer.putAll(pieceMap);
        this.statsOp = new StatsOpImpl();
        this.record = this.cache.config().record();
        // config expire at time
        this.expireAt = ExpireUtils.expireAt(this.cache.config().expire());
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public CacheWrapper cache() {
        return this.cache;
    }

    @Override
    public long expireAt() {
        return this.expireAt;
    }

    @Override
    public void resetExpireAt() {
        this.pieceMap().values().forEach(p -> {
            p.needReload(true);
        });
        this.expireAt = ExpireUtils.expireAt(this.cache.config().expire());
    }

    @Override
    public Piece piece(String name) {
        return meContainer.get(name.toUpperCase());
    }

    @Override
    public void addPiece(String name, Piece piece) {
        this.meContainer.put(name.toUpperCase(), piece);
    }

    @Override
    public void removePiece(String name) {
        this.meContainer.remove(name.toUpperCase());
    }

    @Override
    public CacheValueView getRaw(String key, PieceHolder holder) {
        if (this.record) {
            this.statsOp.increHit();
        }
        long current = System.currentTimeMillis();
        if ((current - this.expireAt) <= 0){
            this.resetExpireAt();
        }
        return toValueView(this.cache.cache().get(key, holder));
    }

    @Override
    public Object removeRaw(String key, EraseHolder holder) {
        if (this.record) {
            this.statsOp.increRemove();
        }
        return this.cache.cache().invalidate(key, holder);
    }

    @Override
    public void putRaw(String key, Object val, PieceHolder holder) {
        if (this.record) {
            statsOp.increPenetrate();
        }
        this.cache.cache().put(key, val, holder);
    }

    @Override
    public Stats stats() {
        return this.statsOp;
    }

    @Override
    public Map<String, Piece> pieceMap() {
        return this.meContainer;
    }

    @Override
    public boolean containsPiece(String name) {
        return this.meContainer.containsKey(name.toUpperCase());
    }


    public void reload() {
        this.cache().cache().invalidateAll();
        this.cache.lastRefreshedTime(System.currentTimeMillis());
    }

    private CacheValueView toValueView(Object value) {
        if (value == null) {
            return new NullCacheValueView();
        }
        return new DefaultCacheValueView(value);
    }


}
