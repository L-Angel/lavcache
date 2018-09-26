package com.langel.lavcache.cache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 * The constructor of the implement of cache interface must has one parameter and the type is String,
 * as to be the name of cache.
 **/
public interface Cache {

    String name();

    Object get(Object key);

    void put(Object key, Object val);

    boolean exits(String key);
}
