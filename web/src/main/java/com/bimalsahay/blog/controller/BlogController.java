package com.bimalsahay.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.db.service.BlogService;
import com.bimalsahay.blog.model.AccountUser;
import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;

import com.bimalsahay.blog.db.service.UserService;

@Controller
public class BlogController {
	
	@Autowired BlogService blogService;
	@Autowired UserService userService;
	
	
	@RequestMapping(value="/blog", method = RequestMethod.GET)
	public String writeBlog(Model model) throws IOException {		
		return "blog";
	}
	
	
	@RequestMapping(value="/blog", method = RequestMethod.POST)
	@ResponseBody
	public List<Blog> drafts(@RequestBody Blog blog) throws Exception {
		
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			List<Blog> draftBlogs = blogService.findByBlogStatus(BlogStatus.DRAFT, user.getId());
			
			if(draftBlogs != null && draftBlogs.size() > 0) {
				return draftBlogs;
			}
			else {
				blog.setUserId(user.getId());				
				blog = blogService.createBlog(blog);
				user.getBlogs().add(blog.getId());
				userService.updateUser(user);
				draftBlogs = new ArrayList<Blog>();
				draftBlogs.add(blog);
				return draftBlogs;
			}
		}
		return null;
	}
	
	
	@RequestMapping(value="/user/draft", method = RequestMethod.POST)
	@ResponseBody
	public Blog createDraftBlog(@RequestBody Blog blog) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		blog.setUserId(user.getId());
		blog.setBlogStatus(BlogStatus.DRAFT);
		Blog newBlog = blogService.createBlog(blog);
		//user.getBlogId().add(newBlog.getId());
		//userService.updateUser(user);
		return blog;
	}

	
	@RequestMapping(value="/user/createblog", method = RequestMethod.POST)
	@ResponseBody
	public Blog createBlog(@RequestBody Blog blog) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		blog.setUserId(user.getId());
		blog.setBlogStatus(BlogStatus.PUBLISHED);
		Blog newBlog = blogService.createBlog(blog);
		//user.getBlogId().add(newBlog.getId());
		//userService.updateUser(user);
		return blog;
	}

	@RequestMapping(value="/user/addblogimage", method = RequestMethod.POST)
	@ResponseBody
	public Blog addBlogImage(@RequestParam(value = "blogId", required = false) String blogId, @RequestParam(value = "file", required = false) List<CommonsMultipartFile> files, 
			MultipartHttpServletRequest mrequest) throws IOException {
		for (CommonsMultipartFile commonsMultipartFile : files) {
			System.out.println(commonsMultipartFile.getName());
		}
/*		Blog blog = blogService.findById(blogId);
		blogService.addBlogImage(blog, fileToUpload);*/

		return null;
	}
	
}
