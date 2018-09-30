package com.langel.lavcache.test;

import com.langel.lavcache.annotation.Erase;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.PieceKey;
import com.langel.lavcache.annotation.Sector;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
@Sector("MyTestSector")
public class MySector {

    @Piece(prefix = "piece", preload = true, after = {PreloadPieceAction.class})
    public String preloadPiece() {
        System.out.println("With no hit cache");
        return "PreLoadTest";
    }

    @Piece(prefix = "testkey#userId")
    public String piece(@PieceKey(field = "userId") String userId) {
        System.out.println("With no hit cache");
        return "MyTestPiece";
    }

    @Erase(prefix = "testkey#userId")
    public String erase(@PieceKey(field = "userId") String userId) {
        return "";
    }

    @Piece(prefix = "testkey2")
    public String pieceMultiParam(@PieceKey(field = "userId") String userId,
                                  @PieceKey(field = "key2") String keys) {
        System.out.println("With no hit cache");
        return "MyTestPiece";
    }

    @Erase(prefix = "testkey2")
    public String eraseMultiParam(@PieceKey(field = "userId") String userId,
                                  @PieceKey(field = "key2") String keys) {
        System.out.println("With no hit cache");
        return "MyTestPiece";
    }
}
