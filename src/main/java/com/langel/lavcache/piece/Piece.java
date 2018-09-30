package com.langel.lavcache.piece;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Piece {

    String name();

    PieceHolder holder();

    boolean needReload();

    void needReload(boolean need);
}
