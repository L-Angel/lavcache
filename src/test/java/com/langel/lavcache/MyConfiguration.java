package com.langel.lavcache;

import com.langel.lavcache.cache.CachePool;
import com.langel.lavcache.cache.CacheWrapper;
import com.langel.lavcache.cache.builder.CacheBuilder;
import com.langel.lavcache.cache.support.RedisCache;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public class MyConfiguration {
    public static final String TEST_BASE_PACKAGE = "com.langel.lavcache.test";

    public static void buildRedisCache() {
        CacheBuilder builder = new DefaultCacheBuilder();
        CacheWrapper cache = builder.setName("RedisCache")
                .setCacheType(RedisCache.class)
                .build();
        CachePool.add(cache);
    }
}
