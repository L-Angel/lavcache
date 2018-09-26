package com.langel.lavcache.piece;

import java.lang.reflect.Method;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Piece {

    String name();

    Method method();

    Object instance();

    PieceOption option();
}
