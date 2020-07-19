package org.example;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        Jedis redisClient = RedisUtil.getJedis();

        // set 操作
        String key = "test";
        redisClient.sadd(key, "1", "2", "3", "3");

        // 一把获取所有值
        Set<String> setResult = redisClient.smembers(key);
        for (String str : setResult) {
            System.out.println("set value: " + str);
        }

        // 删除一个1
        Long re = redisClient.srem(key, "2");
        System.out.println("delete result: " + re);

        re = redisClient.del(key);
        System.out.println("delete result: " + re);

        redisClient.close();
    }
}
