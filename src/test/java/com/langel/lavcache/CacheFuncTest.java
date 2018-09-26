package com.langel.lavcache;

import com.langel.lavcache.cache.builder.CacheBuilder;
import com.langel.lavcache.cache.builder.DefaultCacheBuilder;
import com.langel.lavcache.cache.support.ConcurrentCache;
import com.langel.lavcache.scanner.PackageScanner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/25
 **/
@RunWith(PowerMockRunner.class)
public class CacheFuncTest {

    @Test
    public void cacheTest() {
        PackageScanner scanner=new PackageScanner();
        scanner.scan(MyConfiguration.TEST_BASE_PACKAGE);
        CacheBuilder builder = new DefaultCacheBuilder();
        builder.setCacheType(ConcurrentCache.class).setName("UnitTestCache");
        Container.INSTANCE.sector("MyTestSector").get("MyTestPiece").value();
        Container.INSTANCE.sector("MyTestSector").get("MyTestPiece").value();
        Object value = Container.INSTANCE.sector("MyTestSector").get("MyTestPiece").value();
        Assert.assertNotNull(value);

    }
}
