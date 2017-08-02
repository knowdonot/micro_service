package com.duo.user.service.impl;

import org.springframework.stereotype.Component;

import com.duo.user.entity.Menu;
import com.duo.user.entity.User;
import com.duo.user.service.IUserService;
import com.duo.user.util.RespResult;

@Component
public class UserServiceImpl implements IUserService{

	@Override
	public User queryUserById(String id) {
		System.out.println("接口不通 。。。。。。");
		return null;
	}

	@Override
	public Menu queryUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
