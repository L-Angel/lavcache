package com.langel.lavcache.cache.config;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 5:14 PM
 **/
public class CustomizeCacheConfig extends AbstractCacheConfig {

    public CustomizeCacheConfig(long expire, boolean record, boolean autoreload) {
        super(expire, record, autoreload);
    }

}
