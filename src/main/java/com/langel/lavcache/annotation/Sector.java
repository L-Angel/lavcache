package com.langel.lavcache.annotation;

import java.lang.annotation.*;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/20
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Sector {

    String value() default "DefaultSector";

}
