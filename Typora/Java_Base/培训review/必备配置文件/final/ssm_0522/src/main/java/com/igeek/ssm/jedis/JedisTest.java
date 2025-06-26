package com.igeek.ssm.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {


    @Test
    /**
     * 字符串的操作
     */
    public void testJedis_String(){
        Jedis jedis = new Jedis("192.168.198.128", 6379);
        //jedis.set("singleJedis", "hello jedis!"); 添加
        //Long singleJedis = jedis.del("singleJedis");  //删除

        jedis.setex("tempString",30,"temp");    //设置过期时间，这个是30秒后过期销毁


        System.out.println(jedis.get("singleJedis"));
        jedis.close();
    }

    @Test
    /**
     * hash的操作
     */
    public void testJedis_Hash(){
        Jedis jedis = new Jedis("192.168.198.128", 6379);

        Map<String,String> hash = new HashMap<>();
        hash.put("1","Jack");
        jedis.hset("students", hash);
        jedis.hset("students","2","Rose");
        jedis.hset("students","2","Rose");

        jedis.hdel("students","2");

        Map<String, String> students = jedis.hgetAll("students");
        Set<Map.Entry<String, String>> entries = students.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        jedis.close();
    }


    @Test
    /**
     * list的操作
     */
    public void testJedis_List(){
        Jedis jedis = new Jedis("192.168.198.128", 6379);

        jedis.del("list");
        jedis.lpush("list","Jack","Tom");
        jedis.lpush("list","Rose");
        jedis.rpush("list","Michael");

        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list);
        String list1 = jedis.rpop("list");
        System.out.println(list1);

        jedis.close();
    }


    @Test
    /**
     * set的操作
     */
    public void testJedis_Set(){
        Jedis jedis = new Jedis("192.168.198.128", 6379);

        jedis.sadd("set","a","b","c","Java","c");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);

        jedis.close();
    }

    @Test
    /**
     * sortedset的操作
     */
    public void testJedis_Sortedset(){
        Jedis jedis = new Jedis("192.168.198.128", 6379);
        jedis.del("sortedset");
        jedis.zadd("sortedset",1,"Jack");
        jedis.zadd("sortedset",2,"Rose");
        jedis.zadd("sortedset",4,"Tom1");
        jedis.zadd("sortedset",4,"Aom2");
        jedis.zadd("sortedset",4,"Tom3");
        jedis.zadd("sortedset",-1,"Jerry");

        Set<String> sortedset = jedis.zrange("sortedset", 0, -1);
        System.out.println(sortedset);

        jedis.close();
    }

    @Test
    /**
     * jedis连接池使用
     */
    public void testJedisPoll(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.198.128", 6379);

        Jedis jedis = jedisPool.getResource();
        jedis.del("sortedset");
        jedis.zadd("sortedset",1,"Jack");
        jedis.zadd("sortedset",2,"Rose");
        jedis.zadd("sortedset",4,"Tom1");
        jedis.zadd("sortedset",4,"Aom2");
        jedis.zadd("sortedset",4,"Tom3");
        jedis.zadd("sortedset",-1,"Jerry");

        Set<String> sortedset = jedis.zrange("sortedset", 0, -1);
        System.out.println(sortedset);

        jedis.close();
    }

}
