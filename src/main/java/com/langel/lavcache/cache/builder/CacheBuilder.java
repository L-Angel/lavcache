package com.langel.lavcache.cache.builder;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.CacheWrapper;
import com.langel.lavcache.cache.config.CacheConfig;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface CacheBuilder {

    CacheWrapper build();

    CacheBuilder setName(String name);

    CacheBuilder setCacheType(Class<? extends Cache> cacheType);

    CacheBuilder setConfig(CacheConfig config);
}
