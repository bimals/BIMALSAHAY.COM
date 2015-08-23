package com.bimalsahay.blog.db.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.db.repository.IBlogRepository;
import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;
import com.bimalsahay.blog.model.SearchCriteria;
import com.mongodb.gridfs.GridFSDBFile;

@Service
@Qualifier("blogService")
public class BlogServiceImpl implements BlogService {

	@Autowired IBlogRepository blogRepository;
	
	public Blog createBlog(Blog blog) throws IOException {

		return blogRepository.createBlog(blog);
	}

	public Blog findById(String blogId) {
		return blogRepository.findById(blogId);
	}

	public void addBlogImage(Blog blog, CommonsMultipartFile fileToUpload) throws IOException {
		
		blogRepository.addBlogImage(blog, fileToUpload);
		
	}

	public List<Blog> searchBlogByKeyWord(SearchCriteria searchCriteria) {
		return blogRepository.searchBlogByKeyWord(searchCriteria);
	}

	public List<GridFSDBFile> getBlogImage(String imageId) {
		return blogRepository.getBlogImage(imageId);
	}

	public List<Blog> findByBlogStatus(BlogStatus status, String userId) {
		return blogRepository.findByBlogStatus(status, userId);
	}


}
