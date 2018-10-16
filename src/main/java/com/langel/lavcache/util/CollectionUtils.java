package com.langel.lavcache.util;

import java.util.Collection;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/30 2:10 PM
 **/
public class CollectionUtils {

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
