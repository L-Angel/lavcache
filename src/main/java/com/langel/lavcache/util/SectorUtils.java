package com.langel.lavcache.util;

import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.sector.SectorImpl;

import java.util.HashMap;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public class SectorUtils {

    public static boolean isSector(Class<?> clazz) {
        return clazz.getAnnotation(com.langel.lavcache.annotation.Sector.class) != null;
    }

    public static Sector toSector(com.langel.lavcache.annotation.Sector sectorAnno) {
        return new SectorImpl(sectorAnno.value(), new HashMap<>());
    }

    public static Sector toSector(String name) {
        return new SectorImpl(name.toUpperCase(), new HashMap<>());
    }
}
