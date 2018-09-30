package com.langel.lavcache.piece;

import java.lang.reflect.Method;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 下午1:44
 **/
public interface EraseHolder extends MethodHolder{

    class Builder {
        public static EraseHolder build(Method m) {
            return new EraseHolderImpl(m);
        }
    }
}
