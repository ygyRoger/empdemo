package com.neuedu.dao;

import org.apache.ibatis.annotations.Param;

import com.neuedu.entity.User;

public interface UserMapper {

	User selectUserByUsername(String username);
	
	int insertUser(@Param("user") User user);
	
}
