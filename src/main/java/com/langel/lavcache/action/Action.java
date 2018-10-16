package com.langel.lavcache.action;

import com.langel.lavcache.piece.MethodHolder;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public interface Action {

    void call(MethodHolder holder, String key, Object val);

}
