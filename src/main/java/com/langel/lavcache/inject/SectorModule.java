package com.langel.lavcache.inject;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.langel.lavcache.annotation.Piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class SectorModule extends AbstractModule {
    @Override
    protected void configure() {
        SectorInterceptor interceptor = new SectorInterceptor();
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Piece.class), interceptor);
    }
}
