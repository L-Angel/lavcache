package com.langel.lavcache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public class ContainerImpl implements Container {


    private static final ConcurrentHashMap<String, Sector> SEC_CONTAINER = new ConcurrentHashMap<>();

    public static Container INSTANCE = new ContainerImpl();

    @Override
    public Sector sector(String name) {
        return SEC_CONTAINER.get(name.toUpperCase());
    }

    @Override
    public void addSector(String name, Sector sector) {
        SEC_CONTAINER.put(name.toUpperCase(), sector);
    }

    @Override
    public boolean exists(String name) {
        return SEC_CONTAINER.containsKey(name.toUpperCase());
    }
}
