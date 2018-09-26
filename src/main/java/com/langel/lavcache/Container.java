package com.langel.lavcache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Container {

    Container INSTANCE = new ContainerImpl();

    Sector sector(String name);

    void addSector(String name, Sector sector);

    boolean exists(String name);

    Collection<String> sectorKeys();
}
