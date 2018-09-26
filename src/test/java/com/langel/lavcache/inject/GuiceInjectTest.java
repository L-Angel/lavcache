package com.langel.lavcache.inject;

import com.langel.lavcache.test.MySector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
@RunWith(PowerMockRunner.class)
public class GuiceInjectTest {

    @Test
    public void injectTest() {
        MySector sector = SectorInjector.getInstance(MySector.class);
        // System.out.println(sector.piece());
        // System.out.println(sector.piece());
        // System.out.println(sector.piece());
        // System.out.println(sector.piece());
        // System.out.println(sector.piece());
        // Assert.assertNotNull(sector.piece());
    }
}
