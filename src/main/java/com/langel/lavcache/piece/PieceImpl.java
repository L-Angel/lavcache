package com.langel.lavcache.piece;

import java.lang.reflect.Method;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class PieceImpl implements Piece {

    private final String name;
    private final Method method;
    private final Object instance;
    private final PieceOption option;

    public PieceImpl(String name, Method method, Object instance, PieceOption option) {
        assert name != null;
        assert method != null;
        assert instance != null;
        assert option != null;

        this.name = name;
        this.method = method;
        this.instance = instance;
        this.option = option;
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

    @Override
    public PieceOption option() {
        return null;
    }
}
