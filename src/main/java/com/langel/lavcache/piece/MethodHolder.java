package com.langel.lavcache.piece;

import com.langel.lavcache.sector.Sector;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 下午1:57
 **/
public interface MethodHolder {

    Method method();

    String prefix();

    Class<?>[] parameterTypes();

    Class<?> targetClass();

    Object target();

    Sector sector();

    Class<?> returnType();
}
