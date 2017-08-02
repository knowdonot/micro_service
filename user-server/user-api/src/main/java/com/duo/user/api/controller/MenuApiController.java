package com.duo.user.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duo.user.api.service.IMenuApiService;
import com.duo.user.entity.Menu;

/**
 * 提供用户信息数据
 * 
 * @Class Name UserController
 * @author yourname
 * @Create In 2017年7月11日
 */
@RestController
@RequestMapping("/menuapi")
public class MenuApiController {

	@Autowired
//	@LoadBalanced
	private IMenuApiService menuApiService;
	
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public List<Menu> findByUserId(@RequestParam("id")String id) {
		return menuApiService.findByUserId(id);
	}

}
