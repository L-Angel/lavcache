package com.langel.lavcache;

import com.langel.lavcache.cache.Cache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Sector {

    String name();

    Cache cache();

    Piece piece(String name);
}
