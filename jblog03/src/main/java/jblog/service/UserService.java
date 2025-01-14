package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		
	    BlogVo blogVo = new BlogVo();
	    blogVo.setBlogId(userVo.getId());
	    blogVo.setTitle(userVo.getName() + "의 블로그");
	    blogVo.setProfile(""); // 변경 해야함
	    blogRepository.insert(blogVo);

	    
	    CategoryVo categoryVo = new CategoryVo();
	    categoryVo.setId(categoryVo.getId());
	    categoryVo.setName("미지정");
	    categoryVo.setDescription("");
	    categoryVo.setBlogId(blogVo.getBlogId());
		categoryRepository.insert(categoryVo);
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}

	public UserVo getUser(String id, String password) {
		UserVo vo = userRepository.findByIdAndPassword(id,password);
		return vo;
		
	}

	public void update(UserVo userVo) {
		userRepository.updateUser(userVo);
	}


}
