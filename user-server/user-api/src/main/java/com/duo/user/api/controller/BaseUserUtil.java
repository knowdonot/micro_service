package com.duo.user.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.duo.user.api.dao.UserDao;
import com.duo.user.entity.User;

/**
 * 提供用户信息
 * @Class Name BaseUserUtil
 * @author yourname
 * @Create In 2017年7月28日
 */
public class BaseUserUtil {

	@Autowired
	private UserDao userDao;
	
	public User getUserByname(String userName){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", userName);
		User user = userDao.getUserByMap(map);
		if(user!=null){
			//放入缓存
			
		}
		return user;
	}
}
