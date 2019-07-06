package com.bkst.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bkst.common.pojo.User;

public interface AccountMapper {
	@Select("SELECT * FROM tb_user WHERE username=#{username} AND password=#{password}")
	User selectUser(User form);

	@Select("SELECT * FROM tb_user WHERE username=#{username}")
	User selectUserByUsername(String username);

	@Insert("INSERT INTO tb_user(username,password,type) VALUES(#{username},#{password},#{type})")
	void insertUser(User user);

}
