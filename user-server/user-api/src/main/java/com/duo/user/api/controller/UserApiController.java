package com.duo.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duo.user.api.service.IUserApiService;
import com.duo.user.entity.User;

/**
 * 提供用户信息数据
 * 
 * @Class Name UserController
 * @author yourname
 * @Create In 2017年7月11日
 */
@RestController
@RequestMapping("/userapi")
public class UserApiController {

	@Autowired
//	@LoadBalanced
	private IUserApiService userApiService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestParam("userName")String userName,@RequestParam("password")String password) {
		
		
		
		return null;
	}

	@RequestMapping(value = "/info/query/{id}", method = RequestMethod.GET)
	public User queryUserById(@PathVariable("id") String id) {
		System.out.println("----queryUserById----------start");
		// 调用服务获取用户信息
		User user = userApiService.queryUserById(id);
		if(user!=null){
			System.out.println("----"+user.getName()+"---------");
		}
		return user;
	}


}
