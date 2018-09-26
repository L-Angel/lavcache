package com.langel.lavcache.util;

import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.piece.Piece;
import com.langel.lavcache.piece.PieceImpl;
import com.langel.lavcache.piece.PieceOption;
import com.langel.lavcache.piece.PieceOptionImpl;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class PieceUtils {

    public static Piece toPiece(com.langel.lavcache.annotation.Piece pieceAnno,
                                Method method,
                                Class<?> clazz) {
        return new PieceImpl(pieceAnno.value(), method, SectorInjector.getInstance(clazz), option(pieceAnno));
    }

    public static PieceOption option(com.langel.lavcache.annotation.Piece pieceAnno) {
        return new PieceOptionImpl(prefix(pieceAnno), pieceAnno.async());
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
}
