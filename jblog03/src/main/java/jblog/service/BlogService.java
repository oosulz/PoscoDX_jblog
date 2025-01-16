package jblog.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.ServletContext;
import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Service
public class BlogService {
	private final BlogRepository blogRepository;

	public BlogService(BlogRepository blogRepository, ServletContext context) {
		this.blogRepository = blogRepository;
	}

	public BlogVo getBlog(String id) {
		return blogRepository.findUserBlog(id);
	}

	public void update(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}
	
}