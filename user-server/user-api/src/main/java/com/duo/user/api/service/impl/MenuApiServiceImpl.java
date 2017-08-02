package com.duo.user.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duo.user.api.dao.MenuDao;
import com.duo.user.api.service.IMenuApiService;
import com.duo.user.entity.Menu;
@Service
public class MenuApiServiceImpl implements IMenuApiService {
	
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> findByUserId(String id) {
//		return menuDao.findByUserId(id);
		return null;
	}
	
	
	

}
