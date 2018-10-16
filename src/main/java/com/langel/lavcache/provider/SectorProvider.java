package com.langel.lavcache.provider;

import com.langel.lavcache.Container;
import com.langel.lavcache.sector.Sector;
import com.langel.lavcache.util.SectorUtils;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 上午11:28
 **/
public class SectorProvider implements Provider<Sector> {

    @Override
    public Sector get(String name) {

        if (Container.INSTANCE.containsSector(name)) {
            return Container.INSTANCE.sector(name);
        }

        Sector sector = SectorUtils.toSector(name);
        Container.INSTANCE.addSector(name, sector);
        return sector;
    }
}
