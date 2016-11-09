package org.liuxinyi.utils.redis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xinyi.Liu on 2016/11/9.
 * @author google
 * @author https://www.zybuluo.com/SailorXiao/note/159072
 */
public class ClusterTest {
    private Set<HostAndPort> clusterNodes;
    private JedisPoolConfig config;
    private JedisCluster jedisCluster;

    private void genClusterNode() {
        clusterNodes = new HashSet<HostAndPort>();
        clusterNodes.add(new HostAndPort("192.168.137.10", 7000));
        clusterNodes.add(new HostAndPort("192.168.137.10", 7001));
        clusterNodes.add(new HostAndPort("192.168.137.10", 7002));
        clusterNodes.add(new HostAndPort("192.168.137.10", 7003));
        clusterNodes.add(new HostAndPort("192.168.137.10", 7004));
        clusterNodes.add(new HostAndPort("192.168.137.10", 7005));
    }

    private void genJedisConfig() {
        config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
    }

    public void clusterInit() {
        genClusterNode();
        genJedisConfig();
        jedisCluster = new JedisCluster(clusterNodes, 5000, config);
    }

    private void clusterSetKey(String key, String value) {
        jedisCluster.set(key, value);
    }


}
