package com.langel.lavcache.sector.record;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/27 下午5:45
 **/
public class StatsOpImpl implements StatsOp {

    /**
     * statistics hit count
     */
    private final AtomicLong hitCount = new AtomicLong(0);

    /**
     * statistic penerate count
     */
    private final AtomicLong penetrateCount = new AtomicLong(0);

    private final AtomicLong removeCount = new AtomicLong(0);

    @Override
    public void addHit(long count) {
        this.hitCount.addAndGet(count);
    }

    @Override
    public void addPenetrate(long count) {
        this.penetrateCount.addAndGet(count);
    }

    @Override
    public void increPenetrate() {
        this.penetrateCount.incrementAndGet();
    }

    @Override
    public void increHit() {
        this.hitCount.incrementAndGet();
    }

    @Override
    public long penetrateCount() {
        return this.penetrateCount.get();
    }

    @Override
    public long hitCount() {
        return this.hitCount.get();
    }

    @Override
    public double hitRate() {
        return (double) this.hitCount.get() /
                (double) (this.penetrateCount.get() + this.hitCount.get());
    }

    @Override
    public long removeCount() {
        return this.removeCount.get();
    }

    @Override
    public long visitCount() {
        return this.penetrateCount.get() + this.hitCount.get() + this.removeCount.get();
    }

    @Override
    public void increRemove() {
        this.removeCount.incrementAndGet();
    }
}
