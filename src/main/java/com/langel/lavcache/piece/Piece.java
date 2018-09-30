package com.langel.lavcache.piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public interface Piece {

    String name();

    PieceHolder holder();

    boolean needReload();

    void needReload(boolean need);
}
