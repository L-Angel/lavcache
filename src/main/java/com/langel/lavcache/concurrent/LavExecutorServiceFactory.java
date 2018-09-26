package com.langel.lavcache.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
public class LavExecutorServiceFactory {

    private static final int THREAD_POOL_CORE_SIZE = 2;
    private static final int THREAD_POOL_MAX_SIZE = 20;
    private static final int THREAD_KEEP_ALIVE = 3;
    private static final int THREAD_POOL_QUEUE_SIZE = 100;
    private static final TimeUnit THREAD_KEEP_ALIVE_TIMEUNIT = TimeUnit.MINUTES;
    private static final ThreadFactory THREAD_FACTORY = new DefaultThreadFactory();

    private static ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(THREAD_POOL_CORE_SIZE,
            THREAD_POOL_MAX_SIZE,
            THREAD_KEEP_ALIVE,
            THREAD_KEEP_ALIVE_TIMEUNIT,
            new LinkedBlockingQueue<>(THREAD_POOL_QUEUE_SIZE),
            THREAD_FACTORY);

    public static ExecutorService executorService() {
        return EXECUTOR_SERVICE;
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "lavcache-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
