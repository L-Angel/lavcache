package com.langel.lavcache.util.pair;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午4:12
 **/
public class Pair<L, R> {

    private L left;

    private R right;

    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L left() {
        return this.left;
    }

    public R right() {
        return this.right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<L, R>(left, right);
    }
}
