package com.neuedu.service;

import com.neuedu.entity.User;

public interface UserService {
	
	User selectUserByUsername(String username);
	
	int insertUser(User user);
	
}
