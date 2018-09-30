package com.langel.lavcache.annotation;

import com.langel.lavcache.action.Action;
import com.langel.lavcache.constant.Expire;

import java.lang.annotation.*;

/**
 * @author jiangcw@Ctrip.com(l-angel)
 * @date 2018/9/20
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Piece {

    /**
     * Piece Name
     *
     * @return
     */
    String value() default "DefaultPiece";

    String prefix() default "";

    /**
     * The option used to close async load cache
     * Default is async.
     *
     * @return
     */
    boolean async() default true;

    long expire() default 1 * Expire.MONTH;

    boolean preload() default false;
    /**
     * @return
     */
    Class<? extends Action>[] after() default {};
}
