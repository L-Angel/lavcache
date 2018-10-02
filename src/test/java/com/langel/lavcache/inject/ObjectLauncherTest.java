package com.langel.lavcache.inject;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.exception.LavCacheException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 2:29 PM
 **/

@RunWith(PowerMockRunner.class)
public class ObjectLauncherTest {

    @Test
    public void loadTest() {
        try {
            Class<? extends Cache> cls = ObjectLauncher.load(null);
            Assert.assertNotNull(cls);
            cls = ObjectLauncher.load("");
            Assert.assertNotNull(cls);
            cls = ObjectLauncher.load("com.lavcache.Test");
            Assert.assertNotNull(cls);
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof LavCacheException);
        }
    }
}
