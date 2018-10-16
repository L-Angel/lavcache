package com.langel.lavcache.inject;

import com.langel.lavcache.annotation.Sector;
import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.exception.LavCacheException;
import com.langel.lavcache.util.ClassUtils;
import com.langel.lavcache.util.StringUtils;


/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 2:04 PM
 **/
public class ObjectLauncher {

    /**
     * according classname  to load class metadata
     *
     * @param classname
     * @return
     * @throws LavCacheException
     */
    public static Class<? extends Cache> loadCache(String classname) throws LavCacheException {
        if (StringUtils.isNullOrEmpty(classname)) {
            throw new LavCacheException("NullArgument Exception");
        }
        try {
            Class<?> cls = Class.forName(classname);

            if (!ClassUtils.isImplement(cls, Cache.class)) {
                throw new LavCacheException(String.format("IllegalArgument Exception,%s is not implement %s interface.",
                        classname, Cache.class.getName()));
            }
            return (Class<? extends Cache>) cls;
        } catch (ClassNotFoundException e) {
            throw new LavCacheException("ClassNotFound Exception", e);
        }
    }

    public static Class<?> loadSector(String classname) throws LavCacheException {
        if (StringUtils.isNullOrEmpty(classname)) {
            throw new LavCacheException("NullArgument Exception");
        }
        try {
            Class<?> cls = Class.forName(classname);
            if (!ClassUtils.isAnnotated(cls, Sector.class)) {
                throw new LavCacheException(String.format("IllegalArgument Exception,%s is not annotated with %s.",
                        classname, Sector.class.getName()));
            }
            return cls;
        } catch (ClassNotFoundException e) {
            throw new LavCacheException("ClassNotFound Exception", e);
        }
    }

}
