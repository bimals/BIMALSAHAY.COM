package com.bimalsahay.blog.db.repository;

import org.springframework.stereotype.Repository;

import com.bimalsahay.blog.model.AccountUser;

@Repository
public interface IUserRepository {
	
    public void createUser(AccountUser user);
    
    public AccountUser findById(String id);
    
    public AccountUser findByUserName(String userName);
     
    public void update(AccountUser user);
}