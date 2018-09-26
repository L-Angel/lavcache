package com.langel.lavcache.inject;

import com.langel.lavcache.Container;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.Sector;
import com.langel.lavcache.cache.view.CacheValueView;
import com.langel.lavcache.cache.view.NullCacheValueView;
import com.langel.lavcache.util.PieceUtils;
import com.langel.lavcache.util.SectorUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class SectorInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method m = invocation.getMethod();
        Piece pieceAnno = m.getAnnotation(Piece.class);
        Class<?> clazz = invocation.getMethod().getDeclaringClass();
        Sector sectorAnno = clazz.getAnnotation(Sector.class);
        com.langel.lavcache.Sector sector =
                Container.INSTANCE.sector(sectorAnno.value());
        if (sector == null) {
            sector = SectorUtils.toSector(sectorAnno);
            Container.INSTANCE.addSector(sectorAnno.value(), sector);
        }
        CacheValueView valueView = sector.getRaw(pieceAnno.value());
        if (valueView instanceof NullCacheValueView) {
            Object r = invocation.proceed();
            if (!sector.containsPiece(pieceAnno.value())) {
                sector.addPiece(pieceAnno.value(), PieceUtils.toPiece(pieceAnno, m, clazz));
            }
            sector.put(pieceAnno.value(), r);
            return r;
        }
        return valueView.value();
    }
}
