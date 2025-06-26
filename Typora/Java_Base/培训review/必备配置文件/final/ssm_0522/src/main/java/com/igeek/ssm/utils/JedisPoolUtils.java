package com.igeek.ssm.utils;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        ClassLoader classLoader = JedisPoolUtils.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("common/jedis.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            String host = properties.getProperty("host");
            System.out.println("host = "+host);
            int port = Integer.parseInt(properties.getProperty("port"));
            int maxTotal = Integer.parseInt(properties.getProperty("maxTotal"));
            int maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxIdle(maxIdle);

            jedisPool = new JedisPool(jedisPoolConfig,host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }




    @Test
    public void testUtils(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("ll","ll");
        System.out.println(jedis.get("ll"));
        jedis.close();
    }


}
