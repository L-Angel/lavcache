package com.langel.lavcache.mock;

import com.langel.lavcache.action.Action;
import com.langel.lavcache.piece.MethodHolder;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 3:42 PM
 **/
public class PreloadPieceAction implements Action {

    @Override
    public void call(MethodHolder holder, String key, Object val) {
        ActionDataCache.DATA = val;
        System.out.println("PreloadPiece action");
        System.out.println(key);
        System.out.println(val);
    }
}
