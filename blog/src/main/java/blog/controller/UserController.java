package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.db.repository.IUserRepository;
import blog.model.AccountUser;

@Controller
public class UserController {
	
	@Autowired IUserRepository userRepository;
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {

		return "login";
	}
	
	@RequestMapping(value="/account/user/login", method = RequestMethod.POST)
	public String login(@RequestBody AccountUser user) throws Exception {

		AccountUser accountUser = userRepository.findByUserName(user.getUserName());
		if(accountUser != null) {
			return "forward:/account/user/loginSuccess";
		}
		else {
			throw new Exception("Login Failed");
		}
	}
	
	@RequestMapping(value="/account/user/loginSuccess")
	public String loginSuccess() {
		
		return "welcome";
	}

}
