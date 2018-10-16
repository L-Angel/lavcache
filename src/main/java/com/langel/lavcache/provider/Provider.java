package com.langel.lavcache.provider;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:07
 **/
public interface Provider<T> {

    T get(String name);
}
