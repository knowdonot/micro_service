package com.duo.user.api.rpc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duo.user.api.dao.UserDao;
import com.duo.user.entity.User;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:15
 */
@Controller
@RequestMapping("rpc/userapi")
public class UserService {
	@Autowired
	private UserDao userDao;

	
	/********************************************/
	
	@RequestMapping(value = "/user/un/{username}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserInfo(@PathVariable("username") String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", username);
		User user = userDao.getUserByMap(map);
		return JSONObject.toJSONString(user);
	}


}
