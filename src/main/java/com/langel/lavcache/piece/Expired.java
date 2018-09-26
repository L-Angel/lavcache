package com.langel.lavcache.piece;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gmail.com)
 * @date 2018/9/26
 **/
public interface Expired {

    long SEC = 1;

    long MIN = 60 * SEC;

    long HOUR = 60 * MIN;

    long DAY = 24 * HOUR;

    long MONTH = 30 * DAY;

    long YEAR = 365 * DAY;
}
