package com.langel.lavcache;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.piece.Piece;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Sector {

    String name();

    Cache cache();

    Piece piece(String name);

    void addPiece(String name, Piece piece);

    void removePiece(String name);

    CacheValueView get(String key, Object... params);

    /**
     * get cache data from cache instance
     *
     * @param key
     * @return
     */
    CacheValueView getRaw(String key);

    void put(String key, Object val);

    boolean containsPiece(String name);
}
