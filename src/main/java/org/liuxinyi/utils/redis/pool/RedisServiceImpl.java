package org.liuxinyi.utils.redis.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis服务
 * 基于Jedis 2.9.0
 * 建议直接使用该类提供的方法
 * <p>
 * 如果不满足可以自己扩展
 * 使用getJedis获取Jedis连接,使用完之后需要closeJedis关闭Jedis连接
 *
 * @see #getJedis
 * @see #closeJedis
 */
@Service("redisService")
@Slf4j
public class RedisServiceImpl implements RedisService {

    private JedisPool jedisPool;


    private synchronized void initPool() {
        try {
            if (null != jedisPool) {
                log.info("already init success");
                return;
            }
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(20);
            config.setMaxWaitMillis(30000);
            config.setMaxIdle(10);
            config.setMinIdle(5);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            JedisPool newJedisPool = new JedisPool(config,
                    "99.48.18.202", 6379, 3000, "1qaz@WSX");
            setJedisPool(newJedisPool);
            log.info("init jedisPool success!");
        } catch (Exception e) {
            log.error("failed to init jedisPool!");
        }
    }

    @Override
    public void reload() {
        initPool();
    }

    /**
     * 获取Jedis连接
     *
     * @return
     */
    private Jedis getJedis() {
        if (null == jedisPool) {
            initPool();
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(3);
        } catch (Exception e) {
            log.error("failed to get jedis resource ", e);
        }
        return jedis;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 关闭连接返回资源
     *
     * @param jedis jedis
     */
    private void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    @Override
    public long expire(String key, int seconds) {
        Jedis jedis = getJedis();
        long result = jedis.expire(key, seconds);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = getJedis();
        long result = jedis.ttl(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = getJedis();
        String result = jedis.set(key, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String set(String key, String value, int seconds) {
        Jedis jedis = getJedis();
        String result = jedis.set(key, value);
        jedis.expire(key, seconds);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String setEx(String key, String value) {
        Jedis jedis = getJedis();
        String result = jedis.setex(key, DEFAULT_EXPIRE_SECONDS, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String setEx(String key, String value, int seconds) {
        Jedis jedis = getJedis();
        String result = jedis.setex(key, seconds, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long setNx(String key, String value) {
        Jedis jedis = getJedis();
        long result = jedis.setnx(key, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long setNx(String key, String value, int seconds) {
        Jedis jedis = getJedis();
        long result = jedis.setnx(key, value);
        jedis.expire(key, seconds);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = getJedis();
        String result = jedis.get(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = getJedis();
        long result = jedis.del(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long increase(String key, int seconds) {
        Jedis jedis = getJedis();
        long result = jedis.incr(key);
        jedis.expire(key, seconds);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long increaseBy(String key, int seconds, long salt) {
        Jedis jedis = getJedis();
        long result = jedis.incrBy(key, salt);
        jedis.expire(key, seconds);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long setAdd(String key, String... members) {
        Jedis jedis = getJedis();
        long result = jedis.sadd(key, members);
        closeJedis(jedis);
        return result;
    }

    @Override
    public boolean setContain(String key, String member) {
        Jedis jedis = getJedis();
        boolean result = jedis.sismember(key, member);
        closeJedis(jedis);
        return result;
    }

    @Override
    public Set<String> setMembers(String key) {
        Jedis jedis = getJedis();
        Set<String> result = jedis.smembers(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long setDel(String key, String... members) {
        Jedis jedis = getJedis();
        long result = jedis.srem(key, members);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long setSize(String key) {
        Jedis jedis = getJedis();
        long result = jedis.scard(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetAdd(String key, String value, double score) {
        Jedis jedis = getJedis();
        long result = jedis.zadd(key, score, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetAdd(String key, HashMap<String, Double> scoreMembers) {
        Jedis jedis = getJedis();
        long result = jedis.zadd(key, scoreMembers);
        closeJedis(jedis);
        return result;
    }

    @Override
    public Set<String> sortedSetMembers(String key) {
        Jedis jedis = getJedis();
        Set<String> result = jedis.zrange(key, 0, -1);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetSize(String key) {
        Jedis jedis = getJedis();
        long result = jedis.zcard(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetCount(String key, double minScore, double maxScore) {
        Jedis jedis = getJedis();
        long result = jedis.zcount(key, minScore, maxScore);
        closeJedis(jedis);
        return result;
    }

    @Override
    public double sortedSetScore(String key, String member) {
        Jedis jedis = getJedis();
        double result = jedis.zscore(key, member);
        closeJedis(jedis);
        return result;
    }

    @Override
    public double sortedSetIncreaseBy(String key, String member, double score) {
        Jedis jedis = getJedis();
        double result = jedis.zincrby(key, score, member);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetDel(String key, String... members) {
        Jedis jedis = getJedis();
        long result = jedis.zrem(key, members);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetDelByScore(String key, double minScore, double maxScore) {
        Jedis jedis = getJedis();
        long result = jedis.zremrangeByScore(key, minScore, maxScore);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long sortedSetDelByRank(String key, int startRank, int endRank) {
        Jedis jedis = getJedis();
        long result = jedis.zremrangeByRank(key, startRank, endRank);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long mapSet(String key, String field, String value) {
        Jedis jedis = getJedis();
        long result = jedis.hset(key, field, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long mapSetNx(String key, String field, String value) {
        Jedis jedis = getJedis();
        long result = jedis.hsetnx(key, field, value);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String mapSet(String key, Map<String, String> hashMap) {
        Jedis jedis = getJedis();
        String result = jedis.hmset(key, hashMap);
        closeJedis(jedis);
        return result;
    }

    @Override
    public Set<String> mapKeys(String key) {
        Jedis jedis = getJedis();
        Set<String> result = jedis.hkeys(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public List<String> mapValues(String key) {
        Jedis jedis = getJedis();
        List<String> result = jedis.hvals(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public Map<String, String> mapGetAll(String key) {
        Jedis jedis = getJedis();
        Map<String, String> result = jedis.hgetAll(key);
        closeJedis(jedis);
        return result;
    }

    @Override
    public String mapGet(String key, String field) {
        Jedis jedis = getJedis();
        String result = jedis.hget(key, field);
        closeJedis(jedis);
        return result;
    }

    @Override
    public long mapDel(String key, String... fields) {
        Jedis jedis = getJedis();
        long result = jedis.hdel(key, fields);
        closeJedis(jedis);
        return result;
    }
}
