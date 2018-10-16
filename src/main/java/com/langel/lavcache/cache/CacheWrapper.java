package com.langel.lavcache.cache;

import com.langel.lavcache.cache.config.CacheConfig;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午8:01
 **/
public class CacheWrapper {

    private String name;

    private Cache cache;

    private CacheConfig config;

    private long createdTime;

    private long lastRefreshedTime;

    public CacheWrapper(String name, Cache cache, CacheConfig config) {
        this.name = name;
        this.cache = cache;
        this.config = config;
        this.createdTime = System.currentTimeMillis();
        this.lastRefreshedTime = this.createdTime;
    }

    public void lastRefreshedTime(long lastRefreshedTime) {
        this.lastRefreshedTime = lastRefreshedTime;
    }

    public long lastRefreshedTime() {
        return this.lastRefreshedTime;
    }

    public long createdTime() {
        return this.createdTime;
    }

    public String name() {
        return this.name;
    }

    public Cache cache() {
        return this.cache;
    }

    public CacheConfig config() {
        return this.config;
    }
}
