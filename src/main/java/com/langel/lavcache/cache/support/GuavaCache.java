package com.langel.lavcache.cache.support;

import com.google.common.cache.CacheBuilder;
import com.langel.lavcache.cache.Cache;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class GuavaCache implements Cache {

    private final String name;

    private final com.google.common.cache.Cache cache;

    public GuavaCache(String name, com.google.common.cache.Cache nativeCache) {
        this.name = name;
        this.cache = nativeCache;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Object get(Object key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    public void put(Object key, Object val) {
        this.cache.put(key, val);
    }

    @Override
    public boolean exits(String key) {
        return this.cache.asMap().containsKey(key);
    }
}
