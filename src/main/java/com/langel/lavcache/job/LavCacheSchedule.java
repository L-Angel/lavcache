package com.langel.lavcache.job;

import com.langel.lavcache.concurrent.LavExecutorServiceFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 11:24 AM
 **/
public class LavCacheSchedule {

    private static final Job CACHE_CLEAR_JOB = new CacheClearJob();

    public void start() {
        LavExecutorServiceFactory.scheduleService().schedule(CACHE_CLEAR_JOB::run, 1, TimeUnit.MINUTES);
    }
}
