package com.duo.user.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duo.user.entity.Menu;
import com.duo.user.entity.User;
import com.duo.user.service.impl.UserServiceImpl;

@FeignClient(value="user-api",fallback = UserServiceImpl.class)
public interface IUserService {
	
	
	@RequestMapping(value = "/userapi/info/query/{id}" , method = RequestMethod.GET)
	public User queryUserById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/userapi/menu/query/{id}", method = RequestMethod.GET)
	public Menu queryUserInfoById(@PathVariable("id") String id);
	
	
	
	@RequestMapping(value = "/userapi/test" , method = RequestMethod.GET)
	public String test();
}
