package com.langel.lavcache.loader;

import com.langel.lavcache.job.LavCacheSchedule;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 2:28 PM
 **/
public class AutoReloadCacheLoader implements Loader {

    private final LavCacheSchedule schedule = new LavCacheSchedule();

    @Override
    public boolean load() throws Throwable {
        schedule.start();
        return true;
    }
}
