package com.langel.lavcache.cache.view;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class AbstractCacheKeyView implements CacheKeyView{

    protected Object key;

    protected long expired;


    @Override
    public Object key() {
        return this.key;
    }

    @Override
    public long expired() {
        return this.expired;
    }
}
