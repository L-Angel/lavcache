package com.langel.lavcache.cache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Cache {

    String name();

    Object get(Object key);

    void put(Object key, Object val);
}
