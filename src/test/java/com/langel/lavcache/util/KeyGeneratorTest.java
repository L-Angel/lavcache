package com.langel.lavcache.util;

import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.piece.MethodHolder;
import com.langel.lavcache.piece.PieceHolder;
import com.langel.lavcache.mock.MySector;
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

    private final KeyGenerator generator = new KeyGenerator();

    @Before
    public void before() {
        Class<?> clazz = MySector.class;
        for (Method m : clazz.getDeclaredMethods()) {
            Piece pieceAnno = m.getAnnotation(Piece.class);
            if (pieceAnno != null && m.getName().startsWith("piece")) {
                this.method = m;
                this.pieceAnno = pieceAnno;
                break;
            }
        }
    }

    @Test
    public void generatorTest() {
        MethodHolder holder = PieceHolder.Builder.build(this.method);
        // String expectKey = this.pieceAnno.prefix().toUpperCase();
        String key = generator.generate(holder, new Object[holder.method().getParameterCount()]);
        Assert.assertEquals("TESTKEY2://USERID=;KEY2=;", key);
    }
}
