package com.langel.lavcache.mock;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.CachePool;
import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.PieceHolder;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/10/3 5:33 AM
 **/
public class DemoCustomizeCache implements Cache {
    @Override
    public Object get(String key, PieceHolder holder) {
        return null;
    }

    @Override
    public void put(String key, Object val, PieceHolder holder) {

    }

    @Override
    public boolean exits(String key) {
        return false;
    }

    @Override
    public Object invalidate(String key, EraseHolder holder) {
        return null;
    }

    @Override
    public void invalidateAll() {

    }
}
