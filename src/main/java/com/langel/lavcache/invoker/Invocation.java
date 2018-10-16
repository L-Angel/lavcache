package com.langel.lavcache.invoker;

import org.aopalliance.intercept.Joinpoint;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:46
 **/
public interface Invocation {

    Method method();

    Object[] args();

    Joinpoint joinpoint();

    class Builder {
        public static Invocation build(Object[] args, Method method, Joinpoint jp) {
            return new InvocationImpl(args, method, jp);
        }
    }
}
