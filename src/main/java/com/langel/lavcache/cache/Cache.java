package com.langel.lavcache.cache;


import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.MethodHolder;
import com.langel.lavcache.piece.PieceHolder;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 * The constructor of the implement of cache interface must has one parameter and the type is String,
 * as to be the name of cache.
 **/
public interface Cache {

    Object get(String key, PieceHolder holder);

    void put(String key, Object val, PieceHolder holder);

    boolean exits(String key);

    Object invalidate(String key, EraseHolder holder);

    void invalidateAll();
}
