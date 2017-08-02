package com.duo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duo.user.entity.Menu;
import com.duo.user.entity.User;
import com.duo.user.service.IUserService;
import com.duo.user.util.RespResult;

/**
 * 提供用户信息数据
 * @Class Name UserController
 * @author yourname
 * @Create In 2017年7月11日
 */
@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping(value="/info/{id}", method = RequestMethod.GET)
	public RespResult<User> queryUserById(@PathVariable("id")  String id){
		System.out.println("---user-agent------start");
		//验证参数
		//调用服务获取用户信息
		try {
			User user = userService.queryUserById(id);
			return new RespResult<User>("200", "success", user);
			
		} catch (Exception e) {
			return new RespResult<User>("500", "服务器异常", null);
		}
		
		
	}
	
	
}
