package com.langel.lavcache.loader;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.inject.ObjectLauncher;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.piece.PieceLoader;
import com.langel.lavcache.util.CollectionUtils;
import com.langel.lavcache.yaml.YamlPool;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 10:46 AM
 **/
public class PreLoadSectorLoader implements Loader {
    @Override
    public boolean load() throws Throwable {
        if (CollectionUtils.isNullOrEmpty(YamlPool.INSTANCE.preLoad())) {
            return true;
        }
        PieceLoader loader = SectorInjector.getInstance(PieceLoader.class);
        for (String clsn : YamlPool.INSTANCE.preLoad()) {
            Class<? extends Cache> cls = ObjectLauncher.load(clsn);
            loader.loadPieces(cls);
        }
        return loader.preload();
    }
}
