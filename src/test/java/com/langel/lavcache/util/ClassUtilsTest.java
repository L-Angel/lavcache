package com.langel.lavcache.util;

import com.langel.lavcache.MyConfiguration;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.Sector;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
@RunWith(PowerMockRunner.class)
public class ClassUtilsTest {

    @Test
    public void methodsWithAnnotatedTest() {
        Set<Class<?>> classes = PackageScanner.scan(MyConfiguration.TEST_BASE_PACKAGE);
        Map<String, Method> methodMap = new HashMap<>();
        for (Class<?> clazz : classes) {
            methodMap.putAll(ClassUtils.methodsWithAnnotated(clazz, Piece.class));
        }
        Assert.assertEquals(1,methodMap.size());
    }

}
