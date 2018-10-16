package com.langel.lavcache.yaml;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collection;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 2:37 PM
 **/

@RunWith(PowerMockRunner.class)
public class YamlLoadTest {

    @Test
    public void loadTest() {
        String name = "lavcache.yml";
        LavCacheConfigEntity config = YamlLoader.load(name, LavCacheConfigEntity.class);
        Assert.assertTrue(config != null && config.getCache().size() > 0);
        Assert.assertTrue(config.getPreload() != null && config.getPreload().size() > 0);
    }

    @Test
    public void defaultLoadTest2() {
        Collection<String> preloads = YamlPool.INSTANCE.preLoad();
        System.out.println(preloads);
        Assert.assertNotNull(preloads);
    }
}
