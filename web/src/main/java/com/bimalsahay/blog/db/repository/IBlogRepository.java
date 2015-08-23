package com.bimalsahay.blog.db.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;
import com.bimalsahay.blog.model.SearchCriteria;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public interface IBlogRepository {
	
    public Blog createBlog(Blog user) throws IOException;
    
    public Blog findById(String id);
    
    public Blog findByUserName(String userName);
     
    public void update(Blog user);

	public void addBlogImage(Blog product, CommonsMultipartFile fileToUpload) throws IOException;

	public List<Blog> searchBlogByKeyWord(SearchCriteria searchCriteria);

	public List<GridFSDBFile> getBlogImage(String imageId);

	public List<Blog> findByBlogStatus(BlogStatus status, String userId);
}
