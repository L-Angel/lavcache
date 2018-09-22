package com.langel.lavcache.cache.support;

import com.langel.lavcache.cache.Cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class ConcurrentCache implements Cache {

    private final String name;
    private final ConcurrentHashMap<Object, Object> cache;

    public ConcurrentCache() {
        this("DefaultConcurrentCache");
    }

    public ConcurrentCache(String name) {
        this.name = name;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Object get(Object key) {
        return this.cache.get(key);
    }

    @Override
    public void put(Object key, Object val) {
        this.cache.put(key, val);
    }
}
