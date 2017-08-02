package com.duo.ui.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.duo.user.entity.Menu;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:11
 */
@FeignClient("user-api")
@RequestMapping("rpc/menuapi")
public interface IUserService {
  @RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
  public String getMenusByUsername(@PathVariable("username") String username,@PathVariable("parentId") String parentId);
  
  @RequestMapping(value = "/user/un/{username}/menu/top", method = RequestMethod.GET)
  public String getTopMenuByUser(@PathVariable("username") String username);
  
  @RequestMapping(value = "/user/un/{username}", method = RequestMethod.GET)
  public String getUserInfo(@PathVariable("username") String username);
  
  @RequestMapping(value = "/user/un/{username}/menu/list", method = RequestMethod.GET)
  public String getMenusList(@PathVariable("username") String username);
  
  @RequestMapping(value = "/user/admin/menu/all", method = RequestMethod.GET)
  public String getMenuAll(@RequestParam(value = "name",required=false) String name);
  
  
  @RequestMapping(value = "/user/un/{username}/menu/save", method = RequestMethod.POST)
  public int addMenu(@PathVariable("username") String username,@RequestBody Menu menu);
  
  @RequestMapping(value = "/user/un/{username}/menu/{id}", method = RequestMethod.DELETE)
  public int deleteMenu(@PathVariable("username") String username, @PathVariable("id")String id);
  
  @RequestMapping(value = "/user/un/{username}/menu/update", method = RequestMethod.PUT)
  public int updateMenu(@PathVariable("username") String username,@RequestBody Menu menu);
  
  @RequestMapping(value = "/user/un/menu/{id}", method = RequestMethod.GET)
  public String getMenu(@PathVariable("id") String id);
  
  
  
}
