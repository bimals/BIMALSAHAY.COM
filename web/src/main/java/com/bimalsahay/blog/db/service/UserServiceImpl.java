package com.bimalsahay.blog.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bimalsahay.blog.model.AccountUser;

import com.bimalsahay.blog.db.repository.IUserRepository;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {

	@Autowired IUserRepository userRepository;

	public UserDetails findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountUser getLoggedInUser() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object loggedInUser = (auth != null) ? auth.getPrincipal() :  null;
		
		if (loggedInUser instanceof AccountUser) {
			return (AccountUser) loggedInUser;
		}
		else {
			return null;
		}
	}

	public void updateUser(com.bimalsahay.blog.model.AccountUser user) {
		userRepository.update(user);
		
	}

}
