package com.langel.lavcache.util;

import com.langel.lavcache.MyConfiguration;
import com.langel.lavcache.annotation.Sector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
@RunWith(PowerMockRunner.class)
public class PackageScannerTest {

    @Test
    public void scanTest() {
        Set<Class<?>> classes = PackageScanUtils.scan(MyConfiguration.TEST_BASE_PACKAGE);
        Assert.assertEquals(1, classes.size())
        ;
        Set<Class<?>> classes2 = PackageScanUtils.scan(MyConfiguration.TEST_BASE_PACKAGE, Sector.class);
        Assert.assertEquals(1, classes2.size());
    }
}
