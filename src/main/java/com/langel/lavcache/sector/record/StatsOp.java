package com.langel.lavcache.sector.record;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/27 下午5:40
 **/
public interface StatsOp extends Stats {

    void addHit(long count);

    void addPenetrate(long count);

    void increPenetrate();

    void increHit();

    void increRemove();
}
