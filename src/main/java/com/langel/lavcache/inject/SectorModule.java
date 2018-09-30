package com.langel.lavcache.inject;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.langel.lavcache.annotation.Erase;
import com.langel.lavcache.annotation.Piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class SectorModule extends AbstractModule {

    /**
     * config Guice
     */
    @Override
    protected void configure() {

        SectorInterceptor sectorInterceptor = new SectorInterceptor();
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Piece.class), sectorInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Erase.class), sectorInterceptor);
    }
}
