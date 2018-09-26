package com.langel.lavcache.cache.view;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public interface CacheKeyView {

    Object key();

    long expired();
}
