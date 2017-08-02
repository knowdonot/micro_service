package com.duo.user.service.impl;

import org.springframework.stereotype.Component;

import com.duo.user.entity.User;
import com.duo.user.service.IMenuService;

@Component
public class MenuServiceImpl implements IMenuService{

	@Override
	public User queryUserById(String id) {
		System.out.println("接口不通 。。。。。。");
		return null;
	}

}
