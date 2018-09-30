package com.langel.lavcache.yaml;

import java.util.Collection;
import java.util.List;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/28 2:44 PM
 **/
public class LavCacheConfigEntity {

    /**
     * cache config
     */
    private List<LavCacheItemEntity> cache;

    /**
     * direct preload sectors
     */
    private Collection<String> preload;

    /**
     * load sectors by api style
     */
    private Collection<String> sectors;

    /**
     * Default rediscache implement config
     */
    private RedisInforEntity redis;

    public Collection<String> getSectors() {
        return sectors;
    }

    public void setSectors(Collection<String> sectors) {
        this.sectors = sectors;
    }

    public RedisInforEntity getRedis() {
        return redis;
    }

    public void setRedis(RedisInforEntity redis) {
        this.redis = redis;
    }

    public Collection<String> getPreload() {
        return preload;
    }

    public void setPreload(Collection<String> preload) {
        this.preload = preload;
    }

    public List<LavCacheItemEntity> getCache() {
        return cache;
    }

    public void setCache(List<LavCacheItemEntity> cache) {
        this.cache = cache;
    }
}
