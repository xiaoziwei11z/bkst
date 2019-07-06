package com.bkst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bkst.common.pojo.User;
import com.bkst.mapper.AccountMapper;

import redis.clients.jedis.JedisCluster;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private JedisCluster jedisClient;
	
	@Override
	public User findUser(User form) {
		return accountMapper.selectUser(form);
	}

	@Override
	public boolean register(User user) {
		User result = accountMapper.selectUserByUsername(user.getUsername());
		if(result != null) {
			return false;	
		}else {
			user.setType(0);
			accountMapper.insertUser(user);
			return true;
		}
	}

	@Override
	public void setUser(String token, User user) {
		jedisClient.set(token, JSON.toJSONString(user));
		jedisClient.expire(token, 60*30);
	}

}
