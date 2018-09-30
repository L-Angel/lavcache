package com.langel.lavcache.cache.config;

import com.langel.lavcache.constant.Expire;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午8:43
 **/
public class DefaultCacheConfig extends AbstractCacheConfig {
    public DefaultCacheConfig() {
        super(Expire.MONTH * 1, false, false);
    }
}
