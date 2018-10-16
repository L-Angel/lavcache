package com.langel.lavcache.mock;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.Sector;

import java.time.LocalDateTime;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/10/4 10:55 PM
 **/
@Sector("DemoCustomizeCache")
public class DemoSector {

    @Piece(prefix = "CustomizePrefix", preload = true)
    public String preLoadPiece() {
        System.out.println(String.format("DmmoCustomizeCacheData, %s", LocalDateTime.now()));
        return "DmmoCustomizeCacheData";
    }
}
