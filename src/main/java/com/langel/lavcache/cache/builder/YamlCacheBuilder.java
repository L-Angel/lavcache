package com.langel.lavcache.cache.builder;

import com.langel.lavcache.yaml.LavCacheItemEntity;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 11:33 PM
 **/
public interface YamlCacheBuilder extends CacheBuilder {

   CacheBuilder setYaml(LavCacheItemEntity config);
}
