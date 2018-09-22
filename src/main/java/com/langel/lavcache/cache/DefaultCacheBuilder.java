package com.langel.lavcache.cache;

import com.langel.lavcache.cache.support.ConcurrentCache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class DefaultCacheBuilder implements Builder {

    private static final String DEFAULT_CACHE_NAME = "DefaultConcurrentCacheName";

    @Override
    public Cache build() {
        return new ConcurrentCache(DEFAULT_CACHE_NAME);
    }
}
