package com.bkst.service;

import com.bkst.common.pojo.User;

public interface AccountService {

	User findUser(User form);

	boolean register(User user);

	void setUser(String uuid,User user);
}
