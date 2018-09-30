package com.langel.lavcache.cache.builder;

import com.langel.lavcache.cache.config.CustomizeCacheConfig;
import com.langel.lavcache.exception.LavCacheException;
import com.langel.lavcache.inject.ObjectLauncher;
import com.langel.lavcache.yaml.LavCacheItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 11:34 PM
 **/
public class DefaultYamlCacheBuilder extends DefaultCacheBuilder implements YamlCacheBuilder {

    private final Logger LOGGER = LoggerFactory.getLogger(YamlCacheBuilder.class);

    private LavCacheItemEntity config;

    @Override
    public CacheBuilder setYaml(LavCacheItemEntity config) {
        this.config = config;
        CustomizeCacheConfig cacheConfig =
                new CustomizeCacheConfig(this.config.getExpire(), this.config.isRecord(), this.config.isAutoreload());
        try {
            super.setName(this.config.getName())
                    .setCacheType(ObjectLauncher.load(config.getImpl()))
                    .setConfig(cacheConfig);
        } catch (LavCacheException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this;
    }


}
