package com.langel.lavcache.loader;

import com.langel.lavcache.cache.CachePool;
import com.langel.lavcache.cache.builder.DefaultYamlCacheBuilder;
import com.langel.lavcache.util.CollectionUtils;
import com.langel.lavcache.yaml.LavCacheItemEntity;
import com.langel.lavcache.yaml.YamlPool;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 2:33 PM
 **/
public class YamlCacheLoader implements Loader {

    @Override
    public boolean load() throws Throwable {

        if (CollectionUtils.isNullOrEmpty(YamlPool.INSTANCE.caches())) {
            return true;
        }

        for (LavCacheItemEntity cache : YamlPool.INSTANCE.caches()) {
            DefaultYamlCacheBuilder builder = new DefaultYamlCacheBuilder();
            CachePool.add(builder.setYaml(cache)
                    .build());
        }
        return true;
    }
}
