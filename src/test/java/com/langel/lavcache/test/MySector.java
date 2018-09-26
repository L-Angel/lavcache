package com.langel.lavcache.test;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.PieceKey;
import com.langel.lavcache.annotation.Sector;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
@Sector("MyTestSector")
public class MySector {

    @Piece(prefix = "testkey#userId")
    public String piece(@PieceKey(field = "userId") String userId) {
        System.out.println("With no hit cache");
        return "MyTestPiece";
    }
}
