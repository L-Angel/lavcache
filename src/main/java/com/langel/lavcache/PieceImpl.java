package com.langel.lavcache;

import java.lang.reflect.Method;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class PieceImpl implements Piece {

    private final String name;
    private final Method method;
    private final Object instance;

    public PieceImpl(String name, Method method, Object instance) {
        assert name != null;
        assert method != null;
        assert instance != null;

        this.name = name;
        this.method = method;
        this.instance = instance;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Method method() {
        return this.method;
    }

    @Override
    public Object instance() {
        return this.instance;
    }
}
