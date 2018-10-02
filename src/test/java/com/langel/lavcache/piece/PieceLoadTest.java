package com.langel.lavcache.piece;

import com.langel.lavcache.Configuration;
import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.mock.ActionDataCache;
import com.langel.lavcache.mock.MySector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/27 下午9:31
 **/
@RunWith(PowerMockRunner.class)
public class PieceLoadTest {

    @Before
    public void before() {
        Configuration.addPreLoadSector(MySector.class);
    }


    @Test
    public void preloadTest() {
        PieceLoader loader = SectorInjector.getInstance(PieceLoader.class);
        boolean val = loader.preload();
        Assert.assertTrue(val);
        Assert.assertNotNull(ActionDataCache.DATA);
    }
}
