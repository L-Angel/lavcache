package com.langel.lavcache.loader;

import com.langel.lavcache.inject.SectorInjector;
import com.langel.lavcache.mock.DemoSector;
import com.langel.lavcache.sector.Sector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/10/4 9:52 PM
 **/
@RunWith(PowerMockRunner.class)
public class LavCacheLauncherTest {

    @Test
    public void launchTest() {
        LavCacheLauncher launcher = new LavCacheLauncher();
        try {
            boolean success = false;
            success = launcher.launch();
            Assert.assertTrue(success);
            long time = 3 * 60 * 1000;
            while (true) {
                Thread.sleep(100);
                DemoSector sector = SectorInjector.getInstance(DemoSector.class);
                System.out.print(sector.preLoadPiece());
                System.out.println(LocalDateTime.now());
                time -= 100;
                if (time <= 0) {
                    break;
                }
            }
        } catch (Throwable throwable) {
            Assert.fail();
        }

    }
}
