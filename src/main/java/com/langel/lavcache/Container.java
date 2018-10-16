package com.langel.lavcache;

import com.langel.lavcache.sector.Sector;

import java.util.Collection;
import java.util.Map;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
public interface Container {

    Container INSTANCE = new ContainerImpl();

    Sector sector(String name);

    Map<String,Sector> sectorMap();

    void addSector(String name, Sector sector);

    void addSector(Sector sector);

    boolean containsSector(String name);

    Collection<String> sectorKeys();
}
