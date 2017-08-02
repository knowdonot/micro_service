package com.duo.user.api.service;

import com.duo.user.entity.User;

public interface IUserApiService {

	/**
	 * 登陆并返回用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	public User login(String userName,String password) ;
	
	public User queryUserById(String useId);
	
}
