package com.langel.lavcache.inject;

import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.exception.LavCacheException;
import com.langel.lavcache.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 2:04 PM
 **/
public class ObjectLauncher {

    /**
     * according classname  to load class metadata
     * @param classname
     * @return
     * @throws LavCacheException
     */
    public static Class<? extends Cache> load(String classname) throws LavCacheException {
        if (StringUtils.isNullOrEmpty(classname)) {
            throw new LavCacheException("NullArgument Exception");
        }
        try {
            Class<?> cls = Class.forName(classname);
            if (!cls.isAssignableFrom(Cache.class)) {
                throw new LavCacheException("IllegalArgument Exception");
            }
            return (Class<? extends Cache>) cls;
        } catch (ClassNotFoundException e) {
            throw new LavCacheException("ClassNotFound Exception", e);
        }
    }
}
