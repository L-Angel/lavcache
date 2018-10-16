package com.langel.lavcache.cache.builder;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.CacheWrapper;
import com.langel.lavcache.cache.config.CacheConfig;
import com.langel.lavcache.cache.config.DefaultCacheConfig;
import com.langel.lavcache.cache.support.ConcurrentMapCache;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public class DefaultCacheBuilder implements CacheBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheBuilder.class);

    private static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";

    protected String name;

    private CacheConfig config;

    private Class<? extends Cache> cacheType;

    public DefaultCacheBuilder() {
    }

    @Override
    public CacheWrapper build() {
        if (this.config == null) {
            this.config = new DefaultCacheConfig();
        }

        if (StringUtils.isNullOrEmpty(this.name)) {
            this.name = DEFAULT_CACHE_NAME;
        }

        if (cacheType == null) {
            this.cacheType = ConcurrentMapCache.class;
        }

        Cache cache = SectorInjector.getInstance(this.cacheType);
        if (cache == null) {
            cache = SectorInjector.getInstance(ConcurrentMapCache.class);
            LOGGER.error("{} is not found in jvm. sys will load default cache with {}",
                    this.cacheType.getName(), ConcurrentMapCache.class);
        }
        return new CacheWrapper(this.name, cache, this.config);

    }

    @Override
    public CacheBuilder setName(String name) {
        this.name = name.toUpperCase();
        return this;
    }

    @Override
    public CacheBuilder setCacheType(Class<? extends Cache> cacheType) {
        this.cacheType = cacheType;
        return this;
    }

    @Override
    public CacheBuilder setConfig(CacheConfig config) {
        this.config = config;
        return this;
    }
}
