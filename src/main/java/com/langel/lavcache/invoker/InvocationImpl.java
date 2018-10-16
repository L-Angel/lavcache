package com.langel.lavcache.invoker;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:53
 **/
public class InvocationImpl implements Invocation {

    private final Object[] args;

    private final Method method;

    private final Joinpoint jp;

    public InvocationImpl(Object[] args, Method method, Joinpoint jp) {
        this.args = args;
        this.method = method;
        this.jp = jp;
    }

    @Override
    public Method method() {
        return this.method;
    }

    @Override
    public Object[] args() {
        return this.args;
    }

    @Override
    public Joinpoint joinpoint() {
        return this.jp;
    }

}
