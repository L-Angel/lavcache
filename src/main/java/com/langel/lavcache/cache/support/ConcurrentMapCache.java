package com.langel.lavcache.cache.support;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.PieceHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class ConcurrentMapCache implements Cache {

    private final ConcurrentHashMap<Object, Object> cache;


    public ConcurrentMapCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String key, Object val, PieceHolder holder) {
        this.cache.put(key, val);
    }

    @Override
    public Object get(String key, PieceHolder holder) {
        return this.cache.get(key);
    }

    @Override
    public boolean exits(String key) {
        return this.cache.containsKey(key.toUpperCase());
    }

    @Override
    public Object invalidate(String key, EraseHolder holder) {
        return this.cache.remove(key);
    }

    @Override
    public void invalidateAll() {
        this.cache.clear();
    }
}
