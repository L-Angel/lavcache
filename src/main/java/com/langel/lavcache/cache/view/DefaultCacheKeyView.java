package com.langel.lavcache.cache.view;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class DefaultCacheKeyView extends AbstractCacheKeyView {
    public DefaultCacheKeyView(Object key, long expired) {
        this.key = key;
        this.expired = expired;
    }
}
