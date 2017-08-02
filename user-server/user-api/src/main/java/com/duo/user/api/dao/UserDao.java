package com.duo.user.api.dao;

import java.util.Map;

//import org.apache.ibatis.annotations.Mapper;


import org.apache.ibatis.annotations.Mapper;

import com.duo.user.entity.User;

@Mapper
public interface UserDao  {
	
//	@Select("SELECT * FROM sys_user where id = #{id}")
//	public User get(@Param("id") String id);

	
	public User get(String id);
	
	/**
	 * 根据用户提供信息查询，key参照User对象字段
	 * 2017年7月18日
	 * By duoduo
	 * @param map
	 * @return
	 */
	public User getUserByMap(Map<String,Object> map);
}
