package com.bimalsahay.blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;
import com.bimalsahay.blog.model.SearchCriteria;
import com.mongodb.gridfs.GridFSDBFile;

public interface BlogService {
	
	Blog createBlog(Blog blog) throws IOException;

	Blog findById(String blogId);
	
	List<Blog> findByBlogStatus(BlogStatus status,  String userId);

	void addBlogImage(Blog blog, CommonsMultipartFile fileToUpload) throws IOException;

	List<Blog> searchBlogByKeyWord(SearchCriteria searchCriteria);

	GridFSDBFile getBlogImage(String imageId);

}
