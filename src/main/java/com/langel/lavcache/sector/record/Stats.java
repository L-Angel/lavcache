package com.langel.lavcache.sector.record;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/27 下午5:34
 **/
public interface Stats {

    /**
     * Penerate Count
     * the count of hit native storage
     *
     * @return
     */
    long penetrateCount();

    /**
     * Hit Count
     * The count of visit cache
     *
     * @return
     */
    long hitCount();

    /**
     * hitCount/(hitcount+peneratecount)
     *
     * @return
     */
    double hitRate();

    /**
     * The count of remove
     *
     * @return
     */
    long removeCount();

    /**
     * peneratecount+hitcount
     *
     * @return
     */
    long visitCount();


}
