package com.zms.web.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zms
 * Date: 2019/12/21 11:32
 */
public class RedisPool {
    private  static final Logger log= LoggerFactory.getLogger(RedisPool.class);
    static {
        PropertiesUtil.loadProperties("redis.properties");
    }
    /**
     * redis连接池对象
     */
    private static JedisPool pool;
    /**
     * 最大连接数
     */
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20"));
    /**
     *最大空闲数
     */
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","20"));
    /**
     * 最小空闲数
     */
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","3"));
    /**
     * redisip
     */
    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    /**
     * redis端口
     */
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));


    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        config.setBlockWhenExhausted(true);

        pool = new JedisPool(config,redisIp,redisPort,1000*2);
    }

    static{
        initPool();
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }


    public static void close(Jedis jedis){
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            log.error("return redis resource exception", e);
        }
    }
}

