package com.duo.user.api.rpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duo.user.api.dao.MenuDao;
import com.duo.user.api.dao.UserDao;
import com.duo.user.entity.Menu;
import com.duo.user.entity.User;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:15
 */
@Controller
@RequestMapping("rpc/menuapi")
public class MenuService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;

	
	private static String getUserIdInfo(String userName){
		return "1";
	}
	
	@RequestMapping(value = "/user/un/{username}/menu/top", method = RequestMethod.GET)
	@ResponseBody
	public String findUserTopMenu(@PathVariable("username") String username) {
		String userId = getUserIdInfo(username);
		Menu menu = new Menu();
		menu.setUserId(userId);
		menu.setParentId("-1");
		List<Menu> list = menuDao.findMenuByway(menu);
		return JSONObject.toJSONString(list);
	}
	/**
	 * 
	 * 2017年7月25日 By duoduo
	 * 
	 * @param username
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public String getMenusByUsername(@PathVariable("username") String username,
			@PathVariable("parentId") String parentId) {
		//从缓存中去用户信息
		//没有则去数据库查
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", username);
		String userId = userDao.getUserByMap(map).getId();
		if (parentId == null || "".equals(parentId)) {
			parentId = "0";
		}
		map.put("userId", userId);
		map.put("parentId", parentId);
		Menu menu = new Menu();
		menu.setUserId(userId);
		menu.setParentId(parentId);
		List<Menu> list = menuDao.findMenuByway(menu);
		if (list != null && list.size() > 0) {
			for (int i = 0, j = list.size(); i < j; i++) {
				map.put("parentId", list.get(i).getId());
				menu.setParentId(list.get(i).getId());
				list.get(i).setChildren(menuDao.findMenuByway(menu));
			}
		}
		return JSONObject.toJSONString(list);
	}

	@RequestMapping(value = "/user/un/{username}/menu/list", method = RequestMethod.GET)
	@ResponseBody
	public String getMenusList(@PathVariable("username") String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", username);
		String userId = userDao.getUserByMap(map).getId();
		Menu menu = new Menu();
		menu.setUserId(userId);
		map.put("userId", userId);
		List<Menu> list = menuDao.findMenuByway(menu);
		return JSONObject.toJSONString(list);
	}

	@RequestMapping(value = "/user/admin/menu/all", method = RequestMethod.GET)
	@ResponseBody
	public String getMenuAll(@RequestParam(value = "name",required=false)String name) {
		List<Menu> list = null;
		if(name == null){
			list = menuDao.findAllList(null);
		}else{
			Menu menu = new Menu();
			menu.setName(name);
			list = menuDao.findAllList(menu);
		}
		return JSONObject.toJSONString(list);
	}

	

	@RequestMapping(value = "/user/un/{username}/menu/save", method = RequestMethod.POST)
	@ResponseBody
	public int saveMenu(@PathVariable("username") String username,@RequestBody Menu menu) {

		if (menu != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loginName", username);
			User user = userDao.getUserByMap(map);
			menu.preInsert();
			menu.setCreateBy(user.getId());
			menu.setUpdateBy(user.getId());
			int result = menuDao.insert(menu);
			return result;
		}
		return 0;
	}

	@RequestMapping(value = "/user/un/{username}/menu/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteMenu(@PathVariable("username") String username,@PathVariable("id") String id) {
		try {
			return menuDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@RequestMapping(value = "/user/un/{username}/menu/update", method = RequestMethod.PUT)
	@ResponseBody
	public int updateMenu(@PathVariable("username") String username,@RequestBody Menu menu){
		try {
			menu.preUpdate();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loginName", username);
			User user = userDao.getUserByMap(map);
			menu.setUpdateBy(user.getId());
			return menuDao.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	 @RequestMapping(value = "/user/un/menu/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getMenu(@PathVariable("id") String id) {
		if(id!=null&&id.length()>0){
			Menu menu = menuDao.get(id);
			return JSONObject.toJSONString(menu);
		}
		return null;
	}
	/********************* 暂存 **********

	private List<Menu> getMenuTree(List<Menu> menus, String root) {
		List<Menu> list = new ArrayList<Menu>();
		for (Menu m : menus) {
			if (m.getParentId().equals(root)) {

				List<Menu> childrenList = getMenuTree(menus, m.getId());
				if (childrenList != null) {
					m.setChildren(childrenList);
				}
				list.add(m);
			}
		}

		return list;

	}
*************/
}
