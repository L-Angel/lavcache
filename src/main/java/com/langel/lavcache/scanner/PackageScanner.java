package com.langel.lavcache.scanner;


import com.langel.lavcache.*;
import com.langel.lavcache.util.PackageScanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/23 下午8:26
 **/
public class PackageScanner implements Scanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageScanner.class);

    @Override
    public void scan(String basePackgaeName) {
        Set<Class<?>> classes = PackageScanUtils.scan(basePackgaeName);
        Map<String, Sector> sectorMap = new HashMap<>();
        for (Class clazz : classes) {
            com.langel.lavcache.annotation.Sector sectorAnno =
                    (com.langel.lavcache.annotation.Sector) clazz.getAnnotation(com.langel.lavcache.annotation.Sector.class);
            if (sectorAnno == null) {
                continue;
            }
            String sectorName = sectorAnno.value();
            Sector sector;
            if (ContainerImpl.INSTANCE.exists(sectorName)) {
                sector = ContainerImpl.INSTANCE.sector(sectorName);

            } else {
                sector = new SectorImpl(sectorName, null, pieces(clazz));
                ContainerImpl.INSTANCE.addSector(sectorName, sector);
            }

        }
    }

    public Map<String, Piece> pieces(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Object instance = instance(clazz);
        Map<String, Piece> pieceMap = new HashMap<>();
        for (Method m : methods) {
            com.langel.lavcache.annotation.Piece pieceAnno =
                    m.getAnnotation(com.langel.lavcache.annotation.Piece.class);
            String name = pieceAnno.value();
            Piece piece = new PieceImpl(name, m, instance);
            pieceMap.put(name, piece);
        }
        return pieceMap;
    }

    private Object instance(Class<?> clazz) {
        try {
            clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
