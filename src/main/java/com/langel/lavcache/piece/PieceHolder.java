package com.langel.lavcache.piece;

import com.langel.lavcache.action.Action;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public interface PieceHolder extends MethodHolder {

    Class<? extends Action>[] after();

    PieceOption option();

    class Builder {
        public static PieceHolder build(Method method) {
            return new PieceHolderImpl(method);
        }
    }
}
