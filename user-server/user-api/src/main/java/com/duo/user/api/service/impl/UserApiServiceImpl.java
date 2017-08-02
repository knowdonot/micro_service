package com.duo.user.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duo.user.api.dao.UserDao;
import com.duo.user.api.service.IUserApiService;
import com.duo.user.entity.User;
@Service
public class UserApiServiceImpl implements IUserApiService {
	
//
	@Autowired
	private UserDao userDao;
	
	@Override
	public User queryUserById(String useId) {
		return userDao.get(useId);
	}


	@Override
	public User login(String userName, String password) {
		//登陆验证
		

		
		return null;
	}
	
	

}
