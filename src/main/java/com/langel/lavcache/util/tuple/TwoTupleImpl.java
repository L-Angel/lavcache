package com.langel.lavcache.util.tuple;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class TwoTupleImpl<F, S> implements TwoTuple<F, S> {

    private F first;

    private S second;

    public TwoTupleImpl(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public F first() {
        return this.first;
    }

    @Override
    public S second() {
        return this.second ;
    }
}
