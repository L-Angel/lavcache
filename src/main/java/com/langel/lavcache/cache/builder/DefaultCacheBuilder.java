package com.langel.lavcache.cache.builder;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.support.ConcurrentCache;
import com.langel.lavcache.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class DefaultCacheBuilder implements CacheBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCacheBuilder.class);

    private static final String DEFAULT_CACHE_NAME = "DefaultCache";

    protected String name;

    private Class<? extends Cache> cacheType;

    public DefaultCacheBuilder() {
    }

    @Override
    public Cache build() {
        try {
            if (StringUtils.isNullOrEmpty(this.name)) {
                this.name = DEFAULT_CACHE_NAME;
            }
            if (cacheType == null) {
                this.cacheType = ConcurrentCache.class;
            }
            return cacheType.getDeclaredConstructor(String.class).newInstance(this.name);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ConcurrentCache(DEFAULT_CACHE_NAME);
        }
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
}
