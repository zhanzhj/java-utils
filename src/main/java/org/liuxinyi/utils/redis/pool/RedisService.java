package org.liuxinyi.utils.redis.pool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Eric.Liu on 2016/10/9.
 */
public interface RedisService {

    int DEFAULT_EXPIRE_SECONDS = 60 * 60 * 6;

    void reload();

    // key
    long expire(String key, int seconds);

    long ttl(String key);

    // String

    String set(String key, String value);

    String set(String key, String value, int seconds);

    String setEx(String key, String value);

    String setEx(String key, String value, int seconds);

    long setNx(String key, String value);

    long setNx(String key, String value, int seconds);

    String get(String key);

    long del(String key);

    long increase(String key, int seconds);

    long increaseBy(String key, int seconds, long salt);

    // set
    long setAdd(String key, String... members);

    boolean setContain(String key, String member);

    Set<String> setMembers(String key);

    long setDel(String key, String... members);

    long setSize(String key);

    // sorted set
    long sortedSetAdd(String key, String value, double score);

    long sortedSetAdd(String key, HashMap<String, Double> scoreMembers);

    Set<String> sortedSetMembers(String key);

    long sortedSetSize(String key);

    long sortedSetCount(String key, double minScore, double maxScore);

    double sortedSetScore(String key, String member);

    double sortedSetIncreaseBy(String key, String member, double score);

    long sortedSetDel(String key, String... members);

    long sortedSetDelByScore(String key, double minScore, double maxScore);

    long sortedSetDelByRank(String key, int startRank, int endRank);

    // map
    long mapSet(String key, String field, String value);

    long mapSetNx(String key, String field, String value);

    String mapSet(String key, Map<String, String> hashMap);

    Set<String> mapKeys(String key);

    List<String> mapValues(String key);

    Map<String, String> mapGetAll(String key);

    String mapGet(String key, String field);

    long mapDel(String key, String... fields);
}
