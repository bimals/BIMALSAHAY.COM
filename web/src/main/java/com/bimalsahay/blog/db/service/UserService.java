package com.bimalsahay.blog.db.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.bimalsahay.blog.model.AccountUser;

public interface UserService {

	UserDetails findByUserName(String userName);
	AccountUser getLoggedInUser() throws Exception;
	void updateUser(AccountUser user);
}
