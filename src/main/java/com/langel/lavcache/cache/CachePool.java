package com.langel.lavcache.cache;

import com.langel.lavcache.cache.config.CacheConfig;
import com.langel.lavcache.cache.config.DefaultCacheConfig;
import com.langel.lavcache.cache.support.ConcurrentMapCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/25
 **/
public class CachePool {

    /**
     * Default Cache implement
     */
    private static final ConcurrentMapCache DEFAULT_CACHE = new ConcurrentMapCache();

    private static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";
    /**
     * Cache pool, Used to cache the cache instances.
     */
    private static final ConcurrentHashMap<String, CacheWrapper> CACHE_CONTAINER = new ConcurrentHashMap<>();


    static {
        CACHE_CONTAINER.put(DEFAULT_CACHE_NAME,
                new CacheWrapper(DEFAULT_CACHE_NAME, DEFAULT_CACHE, new DefaultCacheConfig()));
    }

    public static void add(CacheWrapper cache) {
        CACHE_CONTAINER.put(cache.name().toUpperCase(), cache);
    }

    public static void add(String name, CacheWrapper cache) {
        CACHE_CONTAINER.put(name.toUpperCase(), cache);
    }

    public static void add(String name, Cache cache, CacheConfig config) {
        CACHE_CONTAINER.put(name.toUpperCase(),
                new CacheWrapper(name.toUpperCase(), cache, config));
    }

    public static void add(String name, Cache cache) {
        add(name, cache, new DefaultCacheConfig());
    }

    public static CacheWrapper cache(String name) {
        return CACHE_CONTAINER.getOrDefault(name.toUpperCase(), defaultCache());
    }

    public static void remove(String name) {
        CACHE_CONTAINER.remove(name.toUpperCase());
    }

    public static CacheWrapper defaultCache() {
        return CACHE_CONTAINER.get(DEFAULT_CACHE_NAME);
    }
}
