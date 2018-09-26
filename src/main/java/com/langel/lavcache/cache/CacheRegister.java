package com.langel.lavcache.cache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class CacheRegister {

    public static void register(Cache cache) {
        CachePool.add(cache.name(), cache);
    }

    public static Cache cache(String name) {
        return CachePool.cache(name);
    }
}
