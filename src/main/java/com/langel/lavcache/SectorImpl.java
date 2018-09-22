package com.langel.lavcache;

import com.langel.lavcache.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class SectorImpl implements Sector {
    private final String name;
    private final Cache cache;
    private static final ConcurrentHashMap<String, Piece> ME_CONTAINER = new ConcurrentHashMap<>();

    public SectorImpl(String name, Cache cache, Map<String, Piece> pieceMap) {
        assert name != null;
        assert cache != null;
        assert pieceMap != null;

        this.name = name;
        this.cache = cache;
        ME_CONTAINER.putAll(pieceMap);
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
        return ME_CONTAINER.get(name.toUpperCase());
    }
}
