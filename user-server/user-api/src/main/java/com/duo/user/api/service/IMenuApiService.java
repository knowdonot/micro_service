package com.duo.user.api.service;

import java.util.List;

import com.duo.user.entity.Menu;

public interface IMenuApiService {

	public List<Menu> findByUserId(String id);

}
