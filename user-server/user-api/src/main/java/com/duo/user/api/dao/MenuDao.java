package com.duo.user.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.duo.common.dao.BaseDao;
import com.duo.user.entity.Menu;

@Mapper
public interface MenuDao extends BaseDao<Menu>{
	

	/**
	 * 根据条件查询查询，userId必填，无层级关系
	 * 2017年7月25日
	 * By duoduo
	 * @param menu
	 * @return
	 */
	public List<Menu> findMenuByway(Menu menu);
	
	
	/**
	 * 获取用户菜单
	 * 2017年7月18日
	 * By duoduo
	 * @param id
	 * @return
	 */
	public List<Menu> findUserMenusByUserId(Map<String,Object> map);
	
	public List<Menu> findUserTopMenu(String userId);
	
}
