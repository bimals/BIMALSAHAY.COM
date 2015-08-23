package com.bimalsahay.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bimalsahay.blog.db.service.BlogService;
import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.SearchCriteria;

@Controller
public class SearchController {
	
	@Autowired BlogService blogService;
		
	@RequestMapping(value="blog/search", method = RequestMethod.POST)
	@ResponseBody
	public List<Blog> searchBlog(@RequestBody SearchCriteria searchCriteria) {
		List<Blog> blogs = blogService.searchBlogByKeyWord(searchCriteria);
		
		return blogs;
	}
}
