package blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.BasicDBList;

public class AccountUser implements Serializable,Cloneable, UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private BasicDBList blogs;
	private BasicDBList followers;
	private BasicDBList followings;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getUsername() {
		return userName;
	}
	public BasicDBList getBlogs() {
		if(blogs == null) {
			blogs = new BasicDBList();
		}
		return blogs;
	}
	public void setBlogs(BasicDBList blogs) {
		this.blogs = blogs;
	}
	public BasicDBList getFollowers() {
		return followers;
	}
	public void setFollowers(BasicDBList followers) {
		this.followers = followers;
	}
	public BasicDBList getFollowings() {
		return followings;
	}
	public void setFollowings(BasicDBList followings) {
		this.followings = followings;
	}

}
