package com.igeek.shop.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    // 创建JedisPool连接池配置
    private static JedisPoolConfig jedisPoolConfig = null;

    private static String host = "192.168.178.188";
    private static int port = 6379;

    // 创建JedisPool连接池对象
    private static JedisPool jedisPool = null;

    public static void init() {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis) {
        jedis.close();
    }

    //关闭对象池资源
    public static void destory() {
        jedisPool.close();
    }

}
