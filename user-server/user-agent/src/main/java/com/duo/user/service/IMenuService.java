package com.duo.user.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duo.user.entity.User;
import com.duo.user.service.impl.MenuServiceImpl;

@FeignClient(value="user-api",fallback = MenuServiceImpl.class)
public interface IMenuService {
	
	
	@RequestMapping(value = "/menuapi/list" , method = RequestMethod.GET)
	public User queryUserById(@PathVariable("id") String id);
	
	
}
