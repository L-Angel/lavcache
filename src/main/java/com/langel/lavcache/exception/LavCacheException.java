package com.langel.lavcache.exception;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 11:39 PM
 **/
public class LavCacheException extends Exception {

    public LavCacheException() {
    }

    public LavCacheException(String message) {
        super(message);
    }

    public LavCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public LavCacheException(Throwable cause) {
        super(cause);
    }

    public LavCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
