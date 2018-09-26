package com.langel.lavcache.scanner;

import com.langel.lavcache.ContainerImpl;
import com.langel.lavcache.MyConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/25
 **/
@RunWith(PowerMockRunner.class)
public class PackageScannerTest {

    @Test
    public void scanTest() {
        PackageScanner scanner = new PackageScanner();
        scanner.scan(MyConfiguration.TEST_BASE_PACKAGE);
        Assert.assertTrue(ContainerImpl.INSTANCE.sectorKeys().size() > 0);
    }
}
