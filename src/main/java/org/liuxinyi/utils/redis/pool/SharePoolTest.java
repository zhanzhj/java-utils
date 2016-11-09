package org.liuxinyi.utils.redis.pool;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Eric.Liu on 2016/11/9.
 */
@Slf4j
public class SharePoolTest {

    public void test1() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //设置Redis信息
        String host = "127.0.0.1";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);
        shardInfo1.setPassword("test123");
        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
        shardInfo2.setPassword("test123");
        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);
        shardInfo3.setPassword("test123");

        //初始化ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2, shardInfo3);
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);

        //进行查询等其他操作
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("test", "test");
            jedis.set("test1", "test1");
            String test = jedis.get("test");
            System.out.println(test);
        } finally {
            //使用后一定关闭，还给连接池
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
