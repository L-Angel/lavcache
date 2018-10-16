package com.langel.lavcache.cache.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.langel.lavcache.cache.Cache;
import com.langel.lavcache.piece.EraseHolder;
import com.langel.lavcache.piece.PieceHolder;
import com.langel.lavcache.yaml.YamlPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Collection;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 下午3:16
 **/
public class RedisCache implements Cache {

    private final Logger LOGGER = LoggerFactory.getLogger(Cache.class);

    private final String redisPrefix = "LAVCACHE#";

    private final Jedis jedis;

    private final ObjectMapper mapper;

    public RedisCache() {
        this.jedis = new Jedis(YamlPool.INSTANCE.redis().getIp(), YamlPool.INSTANCE.redis().getHost());
        this.mapper = new ObjectMapper();
    }

    @Override
    public Object get(String key, PieceHolder holder) {
        try {
            return this.mapper.readValue(this.jedis.get(redisPrefix + key), holder.returnType());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void put(String key, Object val, PieceHolder holder) {
        try {
            this.jedis.set(redisPrefix + key, this.mapper.writeValueAsString(val));
            if (holder.option().expire() > 0) {
                this.jedis.pexpire(redisPrefix + key, holder.option().expire());
            }
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean exits(String key) {
        return this.jedis.exists(redisPrefix + key);
    }

    @Override
    public Object invalidate(String key, EraseHolder holder) {
        return this.jedis.del(redisPrefix + key);
    }

    @Override
    public void invalidateAll() {
        Collection<String> lcKeys = this.jedis.keys(redisPrefix);
        lcKeys.forEach(this.jedis::del);
    }

}
