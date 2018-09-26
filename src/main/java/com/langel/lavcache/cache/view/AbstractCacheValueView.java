package com.langel.lavcache.cache.view;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class AbstractCacheValueView implements CacheValueView {

    protected Object value;

    @Override
    public Object value() {
        return this.value;
    }
}
