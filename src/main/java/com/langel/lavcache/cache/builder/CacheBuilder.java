package com.langel.lavcache.cache.builder;

import com.langel.lavcache.cache.Cache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface CacheBuilder {

    Cache build();

    CacheBuilder setName(String name);

    CacheBuilder setCacheType(Class<? extends Cache> cacheType);
}
