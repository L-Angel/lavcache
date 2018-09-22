package com.langel.lavcache.test;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.Sector;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
@Sector("MyTestSector")
public class MySector {

    @Piece("MyTestPiece")
    public String piece(String key) {
        return "MyTestPiece";
    }
}
