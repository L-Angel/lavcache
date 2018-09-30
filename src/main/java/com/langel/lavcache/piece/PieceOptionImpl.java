package com.langel.lavcache.piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class PieceOptionImpl implements PieceOption {


    private final boolean async;

    private final long expire;

    private final boolean preload;

    public PieceOptionImpl(boolean async,
                           long expire,
                           boolean preload) {
        this.async = async;
        this.expire = expire;
        this.preload = preload;
    }

    @Override
    public boolean async() {
        return this.async;
    }

    @Override
    public long expire() {
        return this.expire;
    }

    @Override
    public boolean preload() {
        return this.preload;
    }
}
