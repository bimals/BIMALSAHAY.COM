package com.bimalsahay.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bimalsahay.blog.model.AccountUser;

import com.bimalsahay.blog.db.repository.IUserRepository;

@Controller
public class RegistrationController {

	@Autowired private IUserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/account/user/new", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {
		return "register";
	}

	@RequestMapping(value="/account/user/create", method = RequestMethod.POST)
	public String createUser(@RequestBody AccountUser user) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object loggedInUser = (auth != null) ? auth.getPrincipal() :  null;
	
		if (loggedInUser instanceof AccountUser) {
			return "home";
		}
		
		
		String j_password = user.getPassword();
	
		if(user.getUserType().equalsIgnoreCase("facebook")) {
			j_password = user.getUserTypeId();
		}

		user.setPassword(passwordEncoder.encode(j_password));	
		
		AccountUser u = userRepository.findByUserName(user.getUserName());
		if(u == null) {
			userRepository.createUser(user);
			u = userRepository.findByUserName(user.getUserName());
		}
		return "forward:/j_spring_security_check?j_username=" + u.getUserName() +"&j_password="+ j_password;

	}
}
