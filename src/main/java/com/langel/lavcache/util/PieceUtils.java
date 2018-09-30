package com.langel.lavcache.util;

import com.langel.lavcache.piece.*;
import com.langel.lavcache.sector.Sector;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class PieceUtils {

    public static boolean isPiece(Method m) {
        return m.getAnnotation(com.langel.lavcache.annotation.Piece.class) != null;
    }

    public static Piece toPiece(PieceHolder holder) {
        Sector sector = holder.sector();
        if (sector.containsPiece(name(holder))) {
            return sector.piece(name(holder));
        }
        return new PieceImpl(name(holder), holder);
    }

    public static PieceOption option(com.langel.lavcache.annotation.Piece pieceAnno) {
        return new PieceOptionImpl(pieceAnno.async(), pieceAnno.expire(), pieceAnno.preload());
    }

    public static String prefix(com.langel.lavcache.annotation.Piece pieceAnno) {
        if (!StringUtils.isNullOrEmpty(pieceAnno.prefix())) {
            return pieceAnno.prefix();
        }
        return pieceAnno.value();
    }

    public static com.langel.lavcache.annotation.Piece pieceAnno(Method method) {
        return method.getAnnotation(com.langel.lavcache.annotation.Piece.class);
    }

    /**
     * METHODNAME#PREFIX
     *
     * @param holder
     * @return
     */
    public static String name(PieceHolder holder) {
        return (holder.method().getName() + "#" + holder.prefix()).toUpperCase();
    }
}
