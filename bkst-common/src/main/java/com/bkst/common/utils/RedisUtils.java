package com.bkst.common.utils;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.bkst.common.pojo.User;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisUtils {
	public static User getUser(String value) {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisClient = new JedisCluster(nodes);
		
		String json = jedisClient.get(value);
		User user = JSON.parseObject(json, User.class);
		
		jedisClient.close();
		return user;
	}

	public static void removeUser(String token) {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisClient = new JedisCluster(nodes);
		
		jedisClient.del(token);
		
		jedisClient.close();
	}
}
