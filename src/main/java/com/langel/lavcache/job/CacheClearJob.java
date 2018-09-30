package com.langel.lavcache.job;

import com.langel.lavcache.Container;
import com.langel.lavcache.constant.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 11:00 AM
 **/
public class CacheClearJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    @Override
    public void run() {
        Container.INSTANCE.sectorMap().values().forEach(v -> {

            v.expire(v.expire() - Delay.MIN);
            if (v.expire() <= 0) {
                v.pieceMap().values().forEach(p -> {
                    p.needReload(true);
                });
                v.expire(v.cache().config().expire());
                LOGGER.warn("[ LavCache ] : Job. Sector {} needReload flag is setted true", v.name());
            }
        });
    }
}
