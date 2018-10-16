package com.langel.lavcache.invoker;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:54
 **/
public interface Invoker {

    Object invoke(Invocation invocation) throws Throwable;

}
