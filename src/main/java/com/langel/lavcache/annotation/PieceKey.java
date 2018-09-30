package com.langel.lavcache.annotation;

import java.lang.annotation.*;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PieceKey {

    String field() default "";
}
