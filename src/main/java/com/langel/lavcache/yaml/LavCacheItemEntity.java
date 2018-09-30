package com.langel.lavcache.yaml;

/**
 * @author L-Angel,Rick(lonelyangel.jcw@gamil.com)
 * @date 2018/9/29 2:35 PM
 **/
public class LavCacheItemEntity {

    /**
     * name
     */
    private String name;

    /**
     * expired time
     */
    private long expire;

    /**
     * class which implement Cache
     */
    private String impl;

    /**
     * record visited log
     */
    private boolean record;

    /**
     * if opne autoreload cache or not
     */
    private boolean autoreload;

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }

    public boolean isAutoreload() {
        return autoreload;
    }

    public void setAutoreload(boolean autoreload) {
        this.autoreload = autoreload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getImpl() {
        return impl;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }
}
