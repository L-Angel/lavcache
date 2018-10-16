package com.langel.lavcache.piece;

import com.langel.lavcache.action.Action;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.provider.Provider;
import com.langel.lavcache.provider.SectorProvider;
import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.util.PieceUtils;

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

    private final Provider<Sector> sectorProvider;

    private Class<?> returnType;

    public PieceHolderImpl(Method method) {
        this.method = method;
        Piece pieceAnno = PieceUtils.pieceAnno(this.method);
        this.prefix = PieceUtils.prefix(pieceAnno);
        this.parameterTypes = this.method.getParameterTypes();
        this.afterActions = pieceAnno.after();
        this.targetClass = this.method.getDeclaringClass();
        this.target = SectorInjector.getInstance(this.targetClass);
        this.option = PieceUtils.option(pieceAnno);
        this.sectorProvider = new SectorProvider();
        this.returnType = this.method.getReturnType();
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

    @Override
    public Class<?> targetClass() {
        return this.targetClass;
    }

    @Override
    public Object target() {
        return this.target;
    }

    @Override
    public Class<?> returnType() {
        return this.returnType;
    }

    @Override
    public Sector sector() {
        com.langel.lavcache.annotation.Sector sectorAnno =
                this.targetClass.getAnnotation(com.langel.lavcache.annotation.Sector.class);
        return this.sectorProvider.get(sectorAnno.value());
    }
}
