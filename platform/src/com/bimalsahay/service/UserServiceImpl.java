package com.bimalsahay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bimalsahay.model.AccountUser;
import com.bimalsahay.repository.IUserRepository;

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
			throw new Exception("User Not Logged In.");
		}
	}

	public void updateUser(com.bimalsahay.model.AccountUser user) {
		userRepository.update(user);
		
	}

}
