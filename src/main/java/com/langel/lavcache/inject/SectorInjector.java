package com.langel.lavcache.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class SectorInjector {
    private static final Module GUICE_MODULE = new SectorModule();
    private static final Injector GUICE_INJECTOR = Guice.createInjector(GUICE_MODULE);

    public static <T> T getInstance(Class<T> clazz) {
        return GUICE_INJECTOR.getInstance(clazz);
    }
}
