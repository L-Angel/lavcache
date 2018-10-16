package com.langel.lavcache.loader;

import com.langel.lavcache.inject.ObjectLauncher;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.piece.PieceLoader;
import com.langel.lavcache.util.CollectionUtils;
import com.langel.lavcache.yaml.YamlPool;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/10/4 10:48 PM
 **/
public class SectorsLoader implements Loader {
    @Override
    public boolean load() throws Throwable {
        if (CollectionUtils.isNullOrEmpty(YamlPool.INSTANCE.sectors())) {
            return true;
        }
        PieceLoader loader = SectorInjector.getInstance(PieceLoader.class);
        for (String clsn : YamlPool.INSTANCE.sectors()) {
            loader.loadPieces(ObjectLauncher.loadSector(clsn));
        }
        return true;
    }
}
