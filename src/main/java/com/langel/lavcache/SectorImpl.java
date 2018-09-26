package com.langel.lavcache;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.cache.view.DefaultCacheValueView;
import com.langel.lavcache.cache.view.NullCacheValueView;
import com.langel.lavcache.piece.Piece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class SectorImpl implements Sector {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sector.class);

    private final String name;
    private final Cache cache;
    private final ConcurrentHashMap<String, Piece> meContainer = new ConcurrentHashMap<>();

    public SectorImpl(String name, Cache cache, Map<String, Piece> pieceMap) {
        assert name != null;
        assert pieceMap != null;

        this.name = name;
        this.cache = cache;
        meContainer.putAll(pieceMap);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Cache cache() {
        return this.cache;
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
    public CacheValueView get(String key, Object... params) {
        Object val = this.cache.get(key.toUpperCase());
        if (val == null) {
            Piece piece = this.meContainer.get(key.toUpperCase());
            val = invoke(piece, params);
        }
        return toValuView(val);
    }

    @Override
    public CacheValueView getRaw(String key) {
        return toValuView(this.cache.get(key.toUpperCase()));
    }

    @Override
    public void put(String key, Object val) {
        this.cache.put(key.toUpperCase(), val);
    }

    @Override
    public boolean containsPiece(String name) {
        return this.meContainer.containsKey(name.toUpperCase());
    }

    private Object invoke(Piece piece, Object... params) {
        Method m = piece.method();
        try {
            Object val = m.invoke(piece.instance(), params);
            this.cache.put(piece.name().toUpperCase(), val);
            return val;
        } catch (Exception e) {
            LOGGER.error(this.name + " : " + piece.name(), e);
            return null;
        }
    }

    private CacheValueView toValuView(Object value) {
        if (value == null) {
            return new NullCacheValueView();
        }
        return new DefaultCacheValueView(value);
    }
}
