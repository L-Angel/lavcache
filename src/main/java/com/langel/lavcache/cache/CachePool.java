package com.langel.lavcache.cache;

import com.langel.lavcache.cache.support.ConcurrentCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/25
 **/
public class CachePool {

    /**
     * Default Cache implement
     */
    private static final ConcurrentCache DEFAULT_CACHE = new ConcurrentCache();

    /**
     * Cache pool, Used to cache the cache instances.
     */
    private static final ConcurrentHashMap<String, Cache> CACHE_CONTAINER = new ConcurrentHashMap<>();

    public static void add(String name, Cache cache) {
        CACHE_CONTAINER.put(name.toUpperCase(), cache);
    }

    public static Cache cache(String name) {
        return CACHE_CONTAINER.getOrDefault(name.toUpperCase(), DEFAULT_CACHE);
    }

    public static Cache defaultCache() {
        return DEFAULT_CACHE;
    }
}
