package com.bimalsahay.blog.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bimalsahay.blog.model.AccountUser;
import com.bimalsahay.blog.model.Product;

@Repository
@Qualifier("userRepository")
public class UserRepository implements IUserRepository {

    @Autowired private MongoOperations mongoOps;
    private static final String USER_COLLECTION = "User";
    
	public void createUser(AccountUser user) {
		Product Product = new com.bimalsahay.blog.model.Product();
		Product.setProductDescription("Test");
		 user.getBlogs().add(Product);
		 user.getBlogs().add("Test1");
		 user.getBlogs().add("Test2");
		 user.getBlogs().add("Test3");
		this.mongoOps.insert(user, USER_COLLECTION);
	}
	public AccountUser findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, AccountUser.class, USER_COLLECTION);
	}
	public AccountUser findByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        return this.mongoOps.findOne(query, AccountUser.class, USER_COLLECTION);
	}
	public void update(AccountUser user) {
		this.mongoOps.save(user, USER_COLLECTION);		
	}
	
}
