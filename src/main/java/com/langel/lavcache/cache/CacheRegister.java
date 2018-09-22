package com.langel.lavcache.cache;

import com.langel.lavcache.cache.support.ConcurrentCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class CacheRegister {
    private static final ConcurrentHashMap<String, Cache> CACHE_CONTAINER = new ConcurrentHashMap<>();
    private static final ConcurrentCache DEFAULT_CACHE = new ConcurrentCache();

    public static void register(Builder builder) {
        Cache cache = builder.build();
        CACHE_CONTAINER.put(cache.name().toUpperCase(), cache);
    }

    public static Cache cache(String name) {
        return CACHE_CONTAINER.getOrDefault(name.toUpperCase(), DEFAULT_CACHE);
    }
}
