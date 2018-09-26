package com.langel.lavcache.util;

import com.langel.lavcache.Container;
import com.langel.lavcache.Sector;
import com.langel.lavcache.SectorImpl;
import com.langel.lavcache.cache.CachePool;

import java.util.HashMap;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class SectorUtils {

    public static Sector toSector(com.langel.lavcache.annotation.Sector sectorAnno) {
        return new SectorImpl(sectorAnno.value(), CachePool.cache(sectorAnno.value()),
                new HashMap<>());
    }
}
