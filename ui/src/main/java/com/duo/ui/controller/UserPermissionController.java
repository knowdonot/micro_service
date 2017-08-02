package com.duo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duo.ui.rpc.IUserService;
import com.duo.user.entity.Menu;

/**
 * ${DESCRIPTION}
 *
 * @author 
 * @create 2017-06-27 12:40
 */
@Controller
public class UserPermissionController {
    @Autowired
    private IUserService userService;
   
    
  
    private static final String rootSysId = "-1";
    private static final String rootMenuId = "0";
    
    @RequestMapping(value = "/user/menu",method = RequestMethod.GET)
    @ResponseBody
    public String getUserMenu(@RequestParam(defaultValue = "1") String parentId){
        return userService.getMenusByUsername(getCurrentUserName(),parentId);
    }
    
   
    @RequestMapping(value = "/user/top/menu",method = RequestMethod.GET)
    @ResponseBody
    public String getUserTopMenu(){
        return userService.getMenusByUsername(getCurrentUserName(),rootMenuId);
    }
    
    
    @RequestMapping(value = "/user/sys",method = RequestMethod.GET)
    @ResponseBody
    public String getUserSystem(){
        return userService.getMenusByUsername(getCurrentUserName(),rootSysId);
    }
    
    @RequestMapping(value = "/menu/list",method = RequestMethod.GET)
    @ResponseBody
    public String getMenuList(){
        return userService.getMenusList(getCurrentUserName());
    }
    
    @RequestMapping(value = "/menu/all",method = RequestMethod.GET)
    @ResponseBody
    public String getMenuAll(String name){
        return userService.getMenuAll(name);
    }
    


    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    @ResponseBody
    public int add(Menu entity){
       System.out.println("entity"+entity);
       return userService.addMenu(getCurrentUserName(), entity);
    }
    
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getMenu(@PathVariable("id")String id){
       System.out.println("entity"+id);
       return userService.getMenu(id);
    }
    
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteMenu(@PathVariable("id")String id){
       System.out.println("entity"+id);
       return userService.deleteMenu(getCurrentUserName(), id);
    }
    
    @RequestMapping(value = "/menu/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public int updateMenu(@PathVariable("id")String id,Menu menu){
       System.out.println("entity"+menu);
       return userService.updateMenu(getCurrentUserName(), menu);
    }
    
    @RequestMapping(value = "/user/info",method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(){
        return userService.getUserInfo(getCurrentUserName());
    }
    
    public String getCurrentUserName(){
//        String authorization = request.getHeader("Authorization");
//        return new String(Base64Utils.decodeFromString(authorization));
        return "admin";
    }
}
