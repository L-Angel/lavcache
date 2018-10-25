package com.langel.lavcache.util;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/10/25 3:02 PM
 **/
public class ExpireUtils {

    public static long expireAt(long expire) {
        if (expire < 0) {
            return -1;
        }
        return System.currentTimeMillis() + expire;
    }
}
