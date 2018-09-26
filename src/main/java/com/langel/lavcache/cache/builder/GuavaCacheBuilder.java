package com.langel.lavcache.cache.builder;

import com.google.common.cache.CacheBuilder;
import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.support.GuavaCache;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class GuavaCacheBuilder extends DefaultCacheBuilder {

    private CacheBuilder builder;

    GuavaCacheBuilder setGuavaBuilder(CacheBuilder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public Cache build() {
        return new GuavaCache(this.name, this.builder.build());
    }
}
