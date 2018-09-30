package com.langel.lavcache.inject;

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
    public void loadTest(){
        Optional<Class<?>> cls=ObjectLauncher.load(null);
        Assert.assertTrue(!cls.isPresent());
        cls=ObjectLauncher.load("");
        Assert.assertTrue(!cls.isPresent());
        cls=ObjectLauncher.load("com.lavcache.Test");
        Assert.assertTrue(!cls.isPresent());
    }
}
