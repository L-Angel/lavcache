package com.langel.lavcache.yaml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 3:04 PM
 * <p>
 * cache config data which load from yml file(resouces/lavcache.yml)
 * Demo :
 * <pre>
 * <code>
 * cache   :
 *   - name   : "DefaultCache" # same as sector name
 *     expire : 100   # ms
 *     record : false # cache visited log
 *     autoreload : false # switch which used to auto reload cache
 *     impl   : com.ctip.flight.mobile.fx.CredisCache.class # class name with implement Cache interface.
 *
 * preload :
 *    - com.langel.lavcache.mock.MySector.class # sector class name with need preload
 *
 * sectors :
 *   - com.langel.lavcache.DemoSector.class
 *
 * redis   :
 *   ip : 127.0.0.1
 *   host : 6379
 * </code>
 * </pre>
 **/
public class YamlPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(YamlPool.class);

    private static volatile LavCacheConfigEntity CACHED_CONFIG;

    private static final String DEFAULT_LAVCACHE_YAML = "lavcache.yml";

    public static YamlPool INSTANCE = new YamlPool();

    public YamlPool() {
        loadLavYaml();
    }

    private void loadLavYaml() {
        if (CACHED_CONFIG == null) {
            load(DEFAULT_LAVCACHE_YAML);
        }
        if (CACHED_CONFIG == null) {
            LOGGER.error("[ LavCache ] : YamlPool - NotFoundYml Config file, filename {}.", DEFAULT_LAVCACHE_YAML);
        }
    }

    public LavCacheConfigEntity config() {
        return CACHED_CONFIG;
    }

    public Collection<LavCacheItemEntity> caches() {
        return CACHED_CONFIG.getCache();
    }

    public Collection<String> preLoad() {
        return CACHED_CONFIG.getPreload();
    }

    public Collection<String> sectors() {
        return CACHED_CONFIG.getSectors();
    }

    public RedisInforEntity redis() {
        return CACHED_CONFIG.getRedis();
    }

    public static void load(String filename) {
        CACHED_CONFIG = YamlLoader.load(filename, LavCacheConfigEntity.class);
    }

}
