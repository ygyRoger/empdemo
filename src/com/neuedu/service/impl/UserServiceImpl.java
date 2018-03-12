package com.neuedu.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.dao.UserMapper;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.util.MyBatisUtil;

public class UserServiceImpl implements UserService{

	@Override
	public User selectUserByUsername(String username) {
		SqlSession session = MyBatisUtil.getSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.selectUserByUsername(username);
		session.close();
		return user;
	}

	@Override
	public int insertUser(User user) {
		SqlSession session = MyBatisUtil.getSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int i = userMapper.insertUser(user);
		session.close();
		return i;
	}

}
