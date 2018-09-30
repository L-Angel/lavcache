package com.langel.lavcache.piece;

import java.lang.reflect.Method;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class PieceImpl implements Piece {

    private final String name;

    private final PieceHolder holder;

    private boolean needReload;

    public PieceImpl(String name, PieceHolder holder) {
        assert name != null;
        assert holder != null;

        this.name = name;
        this.holder = holder;
        this.needReload = false;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public PieceHolder holder() {
        return this.holder;
    }

    @Override
    public boolean needReload() {
        return this.needReload;
    }

    @Override
    public void needReload(boolean need) {
        this.needReload = need;
    }
}
