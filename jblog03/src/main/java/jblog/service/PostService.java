package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jblog.repository.PostRepository;
import jblog.vo.PostVo;


@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public void insertPost(PostVo postVo) {
		postRepository.insertPost(postVo);
	}

}
