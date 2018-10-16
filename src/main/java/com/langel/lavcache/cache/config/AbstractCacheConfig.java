package com.langel.lavcache.cache.config;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午8:42
 **/
public class AbstractCacheConfig implements CacheConfig {

    private final long expire;

    private final boolean record;

    private final boolean autoreload;

    public AbstractCacheConfig(long expire, boolean record, boolean autoreload) {
        this.expire = expire;
        this.record = record;
        this.autoreload = autoreload;
    }

    @Override
    public long expire() {
        return this.expire;
    }

    @Override
    public boolean record() {
        return this.record;
    }

    @Override
    public boolean autoreload() {
        return this.autoreload;
    }
}
