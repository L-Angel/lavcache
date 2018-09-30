package com.langel.lavcache.provider;

import com.langel.lavcache.cache.CachePool;
import com.langel.lavcache.cache.CacheWrapper;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:08
 **/
public class CacheProvider implements Provider<CacheWrapper> {
    public CacheProvider() {
    }

    @Override
    public CacheWrapper get(String name) {
        return CachePool.cache(name.toUpperCase());
    }
}
