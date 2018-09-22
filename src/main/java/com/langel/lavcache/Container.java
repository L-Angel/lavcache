package com.langel.lavcache;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
public interface Container {

    Sector sector(String name);

    void addSector(String name, Sector sector);

    boolean exists(String name);
}
