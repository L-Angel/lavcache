package com.langel.lavcache.cache.config;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午8:00
 **/
public interface CacheConfig {

    long expire();

    boolean record();

    boolean autoreload();
}
