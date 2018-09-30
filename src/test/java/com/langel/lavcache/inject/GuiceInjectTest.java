package com.langel.lavcache.inject;

import com.langel.lavcache.MyConfiguration;
import com.langel.lavcache.test.MySector;
import com.langel.lavcache.test.RedisSector;
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
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        sector.erase("UserId 111");
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.piece("UserId 111"));
        System.out.println(sector.pieceMultiParam("UserId 111","key2"));

        System.out.println(sector.pieceMultiParam("UserId 111","key2"));
        System.out.println(sector.eraseMultiParam("UserId 111","key2"));
        System.out.println(sector.pieceMultiParam("UserId 111","key2"));
        System.out.println(sector.pieceMultiParam("UserId 111","key3"));
        System.out.println(sector.pieceMultiParam("UserId 111","key3"));

        System.out.println(sector.piece("UserId 111"));

        Assert.assertNotNull(sector.piece("test2"));

    }


    @Test
    public void redisInjectTest() {
        MyConfiguration.buildRedisCache();
        RedisSector sector = SectorInjector.getInstance(RedisSector.class);
        System.out.println(sector.redisPiece("key1"));
        System.out.println(sector.redisPiece("key2"));
        System.out.println(sector.redisPiece("key1"));
        System.out.println(sector.redisPiece("key1"));
        System.out.println(sector.redisErase("key1"));
        System.out.println(sector.redisPiece("key1"));
        System.out.println(sector.redisPiece("key1"));
        Assert.assertNotNull(sector.redisPiece("test2"));

    }
}
