package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.GET)
	public String loginForm() {
		
		return "login";
	}
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.POST)
	public String login() {
		
		return "forward:/account/user/loginSuccess";
	}
	
	@RequestMapping(value="/account/user/loginSuccess", method = RequestMethod.POST)
	public String loginSuccess() {
		
		return "welcome";
	}

}
