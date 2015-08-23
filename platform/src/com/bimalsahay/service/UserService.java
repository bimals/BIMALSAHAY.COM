package com.bimalsahay.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.bimalsahay.model.AccountUser;

public interface UserService {

	UserDetails findByUserName(String userName);
	AccountUser getLoggedInUser() throws Exception;
	void updateUser(AccountUser user);
}
