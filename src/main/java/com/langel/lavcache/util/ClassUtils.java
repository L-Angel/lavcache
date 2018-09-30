package com.langel.lavcache.util;

import com.langel.lavcache.annotation.Piece;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public class ClassUtils {

    public static Map<String, Method> methodsWithAnnotated(Class<?> clazz, Class<? extends Piece> anno) {
        Map<String, Method> annotatedMethods = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            Piece piece = method.getAnnotation(anno);
            if (piece != null) {
                annotatedMethods.put(piece.value(), method);
            }
        }
        return annotatedMethods;
    }
}
