package com.langel.lavcache.util;

import com.langel.lavcache.piece.MethodHolder;
import com.langel.lavcache.piece.PieceKey;

import java.lang.annotation.Annotation;
import java.util.LinkedList;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class KeyGenerator {

    /**
     * PREFIX://FIELD1=val1;FIELD2=val2;
     * if parameter annotated with {@link com.langel.lavcache.annotation.PieceKey}
     * first: use field replace all prefix
     * second: cat all parameters with sorted.
     *
     * @param holder
     * @param parameters
     * @return
     */
    public String generate(MethodHolder holder, Object... parameters) {
        if (parameters.length == 0) {
            return holder.prefix();
        }
        StringBuilder prefix = new StringBuilder(holder.prefix().toUpperCase() + "://");
        LinkedList<PieceKey> keys = parameters(holder.method().getParameterAnnotations(), parameters);
        PieceKey pieceKey = null;
        while ((pieceKey = keys.pollFirst()) != null) {
            prefix.append(pieceKey.field().toUpperCase()).append("=").append(pieceKey.val()).append(";");
        }

        return prefix.toString();
    }

    private LinkedList<PieceKey> parameters(Annotation[][] paramterAnnos, Object... parameters) {
        LinkedList<PieceKey> keys = new LinkedList<>();
        if (parameters.length < paramterAnnos.length) {
            return keys;
        }

        for (int idx = 0, len = paramterAnnos.length; idx < len; idx++) {
            Annotation[] pa = paramterAnnos[idx];
            for (Annotation aPa : pa) {
                if (aPa.annotationType().isAssignableFrom(com.langel.lavcache.annotation.PieceKey.class)) {
                    com.langel.lavcache.annotation.PieceKey pieceKeyAnno =
                            (com.langel.lavcache.annotation.PieceKey) aPa;
                    keys.addLast(new PieceKey(pieceKeyAnno.field(), parameters[idx].toString()));
                    break;
                }
            }
        }
        return keys;
    }
}
