package com.langel.lavcache.util;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.test.MySector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
@RunWith(PowerMockRunner.class)
public class KeyGeneratorTest {

    private Method method;

    private Piece pieceAnno;

    @Before
    public void before() {
        Class<?> clazz = MySector.class;
        for (Method m : clazz.getDeclaredMethods()) {
            Piece pieceAnno = m.getAnnotation(Piece.class);
            if (pieceAnno != null) {
                this.method = m;
                this.pieceAnno = pieceAnno;
                break;
            }
        }
    }

    @Test
    public void generatorTest() {
        System.out.println(this.pieceAnno.prefix());
        this.method.getTypeParameters();
        Assert.assertTrue(true);
    }
}
