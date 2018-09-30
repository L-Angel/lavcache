package com.langel.lavcache;

import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.piece.PieceLoader;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class Configuration {


    public static void addPreLoadSector(Class<?> clazz) {
        SectorInjector.getInstance(PieceLoader.class).loadPieces(clazz);
    }
}
