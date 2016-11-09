package org.liuxinyi.utils.redis.pool;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Eric.Liu on 2016/11/9.
 */
@Slf4j
public class PoolTest {

    public void test1() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxWaitMillis(30000);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        JedisPool jedisPool = new JedisPool(config,
                "99.48.18.202", 6379, 3000, "1qaz@WSX");

        Jedis jedis = jedisPool.getResource();
        jedis.set("key", "value");
        jedis.close();
    }
}
