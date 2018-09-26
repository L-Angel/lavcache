package com.langel.lavcache.piece;

import com.google.inject.Guice;
import com.langel.lavcache.action.Action;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.util.PieceUtils;
import jdk.internal.dynalink.linker.MethodHandleTransformer;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class PieceHolderImpl implements PieceHolder {

    private final Method method;

    private final String prefix;

    private final Class<?>[] parameterTypes;

    private final Class<? extends Action>[] afterActions;

    private final PieceOption option;

    private final Class<?> targetClass;

    private final Object target;

    public PieceHolderImpl(Method method) {
        this.method = method;
        Piece pieceAnno = PieceUtils.pieceAnno(this.method);
        this.prefix = PieceUtils.prefix(pieceAnno);
        this.parameterTypes = this.method.getParameterTypes();
        this.afterActions = pieceAnno.after();
        this.targetClass = this.method.getDeclaringClass();
        this.target = SectorInjector.getInstance(this.targetClass);
        this.option = PieceUtils.option(pieceAnno);
    }

    @Override
    public Method method() {
        return this.method;
    }

    @Override
    public String prefix() {
        return this.prefix;
    }

    @Override
    public Class<? extends Action>[] after() {
        return this.afterActions;
    }

    @Override
    public PieceOption option() {
        return this.option;
    }

    @Override
    public Class<?>[] parameterTypes() {
        return this.parameterTypes;
    }
}
