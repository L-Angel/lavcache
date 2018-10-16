package com.langel.lavcache.loader;

import java.util.LinkedList;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 2:47 PM
 **/
public class LavCacheLauncher {

    private static final LinkedList<Loader> LOADERS = new LinkedList<>();

    private void loadDdefaultLoader() {
        LOADERS.addLast(new YamlCacheLoader());
        LOADERS.addLast(new PreLoadSectorLoader());
        LOADERS.addLast(new AutoReloadCacheLoader());
    }

    public LavCacheLauncher() {
        loadDdefaultLoader();
    }

    public boolean launch() throws Throwable {
        for (Loader loader : LOADERS) {
            if (!loader.load()) {
                return false;
            }
        }
        return true;
    }

    public void addFirst(Loader loader) {
        LOADERS.addFirst(loader);
    }

    public void addLast(Loader loader) {
        LOADERS.addLast(loader);
    }

}
