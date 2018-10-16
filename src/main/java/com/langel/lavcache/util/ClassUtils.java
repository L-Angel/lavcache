package com.langel.lavcache.util;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.sector.Sector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
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

    public static boolean isImplement(Class originCls, Class targetCls) {
        boolean flag = false;
        Class superClass = originCls.getSuperclass();
        while (!superClass.equals(Object.class)) {
            if (Arrays.asList(superClass.getInterfaces()).contains(targetCls)) {
                flag = true;
            }
            superClass = superClass.getSuperclass();
        }
        return flag;
    }

    public static boolean isAnnotated(Class origin, Class<? extends Annotation> anno) {
        return origin.getAnnotation(anno) != null;
    }
}
