package com.bimalsahay.blog.db.repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;
import com.bimalsahay.blog.model.SearchCriteria;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
@Qualifier("blogRepository")
public class BlogRepository implements IBlogRepository{
	
	@Autowired private MongoOperations mongoOps;
	private static final String BLOG_COLLECTION = "Blog";

	public Blog createBlog(Blog blog) throws IOException {
		if(blog.getBlogUUID() == null) {
			UUID blogUUID = UUID.randomUUID();
			blog.setBlogUUID(blogUUID);
		}
		if(blog.getCreationTimeStamp() == null) {
			blog.setCreationTimeStamp(new Date());
		}
		this.mongoOps.save(blog, BLOG_COLLECTION);
		return this.findByUUID(blog.getBlogUUID());
	}

	public Blog findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, Blog.class, BLOG_COLLECTION);
	}

	public Blog findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Blog findByUUID(UUID uuid) {
        Query query = new Query(Criteria.where("blogUUID").is(uuid));
        return this.mongoOps.findOne(query, Blog.class, BLOG_COLLECTION);
	}

	public void update(Blog p) {
		this.mongoOps.save(p, BLOG_COLLECTION);		
	}

	public void addBlogImage(Blog blog, CommonsMultipartFile fileToUpload) throws IOException {
        DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		GridFSInputFile gfsFile = gfsPhoto.createFile(fileToUpload.getInputStream());
		gfsFile.setFilename(blog.getId());
		gfsFile.setContentType(fileToUpload.getContentType());
		
		BasicDBObject fileMetadata = new BasicDBObject();
		fileMetadata.put("fileName", fileToUpload.getName());
		fileMetadata.put("originalFileName", fileToUpload.getOriginalFilename());
		fileMetadata.put("contentType", fileToUpload.getContentType());
		fileMetadata.put("fileSize", fileToUpload.getSize());
		fileMetadata.put("storageDescription", fileToUpload.getStorageDescription());
		fileMetadata.put("userId", blog.getUserId());
		
		gfsFile.setMetaData(fileMetadata);
		gfsFile.save();
		
		GridFSDBFile imageForOutput = gfsPhoto.findOne(blog.getId());
		
		blog.setImageId(imageForOutput.getId().toString());
		this.update(blog);
	}

	public List<Blog> searchBlogByKeyWord(SearchCriteria searchCriteria) {
		String searchText = "";
		int skip = 0;
		if(searchCriteria != null && searchCriteria.getSearchText() != null) {
			searchText = searchCriteria.getSearchText();
		}
		
		if(searchCriteria != null && searchCriteria.getSkip() != 0) {
			skip = searchCriteria.getSkip();
		}
		
		Pattern regex = Pattern.compile(".*"+searchText+".*");
		Criteria criteria = Criteria.where("blogName").regex(regex).orOperator(Criteria.where("blogDescription").regex(regex));
		
		if(searchCriteria.getAfter() != null) {
			criteria = Criteria.where("_id").gt(searchCriteria.getAfter()).andOperator(criteria);
		}

		Query query = new Query(criteria).limit(20).with(new Sort(Direction.DESC, "_id"));
		return mongoOps.find(query, Blog.class, BLOG_COLLECTION);
	}

	public List<GridFSDBFile> getBlogImage(String imageId) {
		DB db = mongoOps.getCollection("FILES").getDB();
		GridFS gfsPhoto = new GridFS(db, "FILES");
		return gfsPhoto.find(imageId);
	}

	public List<Blog> findByBlogStatus(BlogStatus status, String userId) {
        Query query = new Query(Criteria.where("blogStatus").is(status).andOperator(Criteria.where("userId").is(userId))).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Blog.class, BLOG_COLLECTION);
	}

	public List<Blog> findAllBlogsByStatus(BlogStatus published, String id) {
        Query query = new Query(Criteria.where("blogStatus").is(published)).with(new Sort(Direction.DESC, "_id"));
        return this.mongoOps.find(query, Blog.class, BLOG_COLLECTION);
	}

}
