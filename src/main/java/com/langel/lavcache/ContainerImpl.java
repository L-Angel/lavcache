package com.langel.lavcache;

import com.langel.lavcache.sector.Sector;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 * The function  of this class as same as sector pool, used to manage the sectors
 **/
public class ContainerImpl implements Container {

    private ConcurrentHashMap<String, Sector> SEC_CONTAINER = new ConcurrentHashMap<>();

    protected ContainerImpl() {

    }

    @Override
    public Sector sector(String name) {
        return SEC_CONTAINER.get(name.toUpperCase());
    }

    @Override
    public Map<String, Sector> sectorMap() {
        return SEC_CONTAINER;
    }

    @Override
    public void addSector(String name, Sector sector) {
        SEC_CONTAINER.put(name.toUpperCase(), sector);
    }

    @Override
    public void addSector(Sector sector) {
        SEC_CONTAINER.put(sector.name().toUpperCase(), sector);
    }

    @Override
    public boolean containsSector(String name) {
        return SEC_CONTAINER.containsKey(name.toUpperCase());
    }

    @Override
    public Collection<String> sectorKeys() {
        return SEC_CONTAINER.keySet();
    }
}
