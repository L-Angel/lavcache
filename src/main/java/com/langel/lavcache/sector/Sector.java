package com.langel.lavcache.sector;

import com.langel.lavcache.cache.CacheWrapper;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.Piece;
import com.langel.lavcache.piece.PieceHolder;
import com.langel.lavcache.sector.record.Stats;

import java.util.Map;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public interface Sector {

    String name();

    CacheWrapper cache();

    Piece piece(String name);

    void addPiece(String name, Piece piece);

    void removePiece(String name);

    /**
     * get cache data from cache instance
     *
     * @param key
     * @return
     */
    CacheValueView getRaw(String key, PieceHolder holder);

    Object removeRaw(String key, EraseHolder holder);

    void putRaw(String key, Object val, PieceHolder holder);

    Stats stats();

    Map<String, Piece> pieceMap();

    boolean containsPiece(String name);

    /**
     * next expired time
     *
     * @return
     */
    long expireAt();

    void resetExpireAt();
}
