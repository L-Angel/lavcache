package com.langel.lavcache.util;

import com.langel.lavcache.annotation.PieceKey;
import com.langel.lavcache.piece.Piece;
import com.langel.lavcache.piece.PieceHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class KeyGenerator {

    public static String generate(PieceHolder holder, Object... parameters) {
        return "";
    }

    private Map<Integer, PieceKey> parameters(Annotation[][] paramterAnnos) {
        Map<Integer, PieceKey> pieceKeyMap = new HashMap<>();
        for (int idx = 0, len = paramterAnnos.length; idx < len; idx++) {
            Annotation[] pa = paramterAnnos[idx];
            for (Annotation aPa : pa) {
                if (aPa.annotationType().isAssignableFrom(PieceKey.class)) {
                    PieceKey pieceKey = (PieceKey) aPa;
                    pieceKeyMap.put(idx, pieceKey);
                }
            }
        }
        return pieceKeyMap;
    }
}
