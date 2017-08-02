package com.duo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登陆
 * @author duoduo
 *
 */
@Controller
public class Login {
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
		System.out.println("login------");
        return "login";
    }

}
