package com.langel.lavcache.piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public interface PieceOption {

    boolean async();

    long expire();

    boolean preload();

}
