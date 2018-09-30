package com.langel.lavcache.test;

import com.langel.lavcache.annotation.Erase;
import com.langel.lavcache.annotation.Piece;
import com.langel.lavcache.annotation.PieceKey;
import com.langel.lavcache.annotation.Sector;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/27 下午3:42
 **/
@Sector("RedisCache")
public class RedisSector {

    @Piece(prefix = "redisPrefix")
    public String redisPiece(@PieceKey(field = "key1") String key) {
        System.out.println("Redis Cache With Nohit Cacche");
        return "rediscache" + key;
    }

    @Erase(prefix = "redisPrefix")
    public Object redisErase(@PieceKey(field = "key1") String key) {
        return "rediscache" + key;
    }
}
