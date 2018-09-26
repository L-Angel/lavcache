package com.langel.lavcache.piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class PieceOptionImpl implements PieceOption{

    private final String name;

    private final boolean async;

    public PieceOptionImpl(String name, boolean async) {
        this.name = name;
        this.async = async;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean async() {
        return this.async;
    }
}
