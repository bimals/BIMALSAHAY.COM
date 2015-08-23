package com.bimalsahay.blog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bimalsahay.blog.db.service.BlogService;
import com.bimalsahay.blog.model.Blog;
import com.bimalsahay.blog.model.BlogStatus;
import com.bimalsahay.model.AccountUser;
import com.bimalsahay.service.UserService;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class BlogController {
	
	@Autowired BlogService blogService;
	@Autowired UserService userService;
	
	
	@RequestMapping(value="/blog", method = RequestMethod.GET)
	public String writeBlog(Model model) throws IOException {		
		return "blog";
	}
	
	@RequestMapping(value="/blog/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Blog getBlog(@PathVariable("id") String id) throws IOException {
		
		return blogService.findById(id);
	}	
	
	@RequestMapping(value="/blog", method = RequestMethod.POST)
	@ResponseBody
	public List<Blog> blogs() throws Exception {
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			return blogService.findByBlogStatus(BlogStatus.PUBLISHED, user.getId());
		}

		return null;
	}
	
	@RequestMapping(value="/blog/draft", method = RequestMethod.POST)
	@ResponseBody
	public List<Blog> drafts() throws Exception {
		
		AccountUser user = userService.getLoggedInUser();
		if(user != null) {
			return blogService.findByBlogStatus(BlogStatus.DRAFT, user.getId());
		}

		return null;
	}
	
	
	@RequestMapping(value="/user/draft", method = RequestMethod.POST)
	@ResponseBody
	public Blog createDraftBlog(@RequestBody Blog blog) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		blog.setUserId(user.getId());
		blog.setBlogStatus(BlogStatus.DRAFT);
		if(blog.getBlogText() != null && blog.getBlogText().length() > 300) {
			blog.setBlogDescription(blog.getBlogText().substring(0, 300));
		}
		else if(blog.getBlogText() != null){
			blog.setBlogDescription(blog.getBlogText());
		}
		blog.setUserFullName(user.getFirstName() + " " + user.getLastName());
		blog.setUserPhotoLink(user.getPhotoLink());
		Blog newBlog = blogService.createBlog(blog);
		
		return newBlog;
	}

	
	@RequestMapping(value="/user/createblog", method = RequestMethod.POST)
	@ResponseBody
	public Blog createBlog(@RequestBody Blog blog) throws Exception {
		AccountUser user = userService.getLoggedInUser();
		blog.setUserId(user.getId());
		blog.setBlogStatus(BlogStatus.PUBLISHED);
		if(blog.getBlogText() != null && blog.getBlogText().length() > 300) {
			blog.setBlogDescription(blog.getBlogText().substring(0, 300));
		}
		else if(blog.getBlogText() != null){
			blog.setBlogDescription(blog.getBlogText());
		}
		blog.setUserFullName(user.getFirstName() + " " + user.getLastName());
		blog.setUserPhotoLink(user.getPhotoLink());
		Blog newBlog = blogService.createBlog(blog);
		//user.getBlogId().add(newBlog.getId());
		//userService.updateUser(user);
		return newBlog;
	}

	@RequestMapping(value="/user/{id}/image", method = RequestMethod.POST)
	@ResponseBody
	public Blog addBlogImage(@PathVariable("id") String id, @RequestParam(value = "file", required = false) List<CommonsMultipartFile> files, 
			MultipartHttpServletRequest mrequest) throws IOException {
		for (CommonsMultipartFile commonsMultipartFile : files) {
			System.out.println(commonsMultipartFile.getName());
			Blog blog = blogService.findById(id);
			if(blog != null) {
				blogService.addBlogImage(blog, commonsMultipartFile);
			}
		}


		return null;
	}
	
	@RequestMapping(value="/blog/{id}/image", method = RequestMethod.GET)
	@ResponseBody
	public byte[][] getBlogImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		int i = 0;
		List<GridFSDBFile> imageForOutput = blogService.getBlogImage(id);
		byte[][] images = new byte[imageForOutput.size()][];
		
		for (GridFSDBFile gridFSDBFile : imageForOutput) {
			if(imageForOutput != null) {
				InputStream is = gridFSDBFile.getInputStream();

				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[16384];
				
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				
				buffer.flush();
				byte[] imagenEnBytes = buffer.toByteArray();

				response.setHeader("Accept-ranges", "bytes");
				response.setContentType("image/jpeg");
				response.setContentLength(imagenEnBytes.length);
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Content-Description", "File Transfer");
				response.setHeader("Content-Transfer-Encoding:", "binary");

				OutputStream out = response.getOutputStream();
				out.write(imagenEnBytes);
				out.flush();
				out.close();
		
				images[i] = data;
				i++;
			}
		}

		
		return images;
	}
	
}
 