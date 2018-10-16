package com.langel.lavcache.piece;

import com.langel.lavcache.annotation.Erase;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.provider.Provider;
import com.langel.lavcache.provider.SectorProvider;
import com.langel.lavcache.sector.Sector;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 下午1:50
 **/
public class EraseHolderImpl implements EraseHolder {

    private final Method method;

    private final String prefix;

    private final Class<?>[] parameterTypes;

    private final Class<?> targetClass;

    private final Object target;

    private final Provider<Sector> sectorProvider;

    private final Class<?> returnType;

    public EraseHolderImpl(Method method) {
        this.method = method;
        this.targetClass = this.method().getDeclaringClass();

        Erase eraseAnno = this.method.getAnnotation(Erase.class);
        if (eraseAnno != null) {
            this.prefix = eraseAnno.prefix();
        } else {
            this.prefix = "";
        }
        this.parameterTypes = this.method().getParameterTypes();
        this.target = SectorInjector.getInstance(this.targetClass);
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
        return sectorProvider.get(sectorAnno.value());
    }

}
