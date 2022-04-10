package com.lwy.demo.StudyRedis;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Redis集群
 */
public class Redis02 {
    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.40.129",6379));
        JedisCluster jedisCluster = new JedisCluster(set);
        jedisCluster.set("k1","v1");
        System.out.println(jedisCluster.get("k1"));
    }
}
