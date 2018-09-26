package com.langel.lavcache.piece;

import com.langel.lavcache.action.Action;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public interface PieceHolder {

    Method method();

    String prefix();

    Class<? extends Action>[] after();

    PieceOption option();

    Class<?>[] parameterTypes();

    class Builder {

        PieceHolder build(Method method) {
            return new PieceHolderImpl(method);
        }
    }
}
