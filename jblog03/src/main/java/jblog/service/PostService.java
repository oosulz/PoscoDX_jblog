package jblog.service;

import java.util.List;

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
	
	public PostVo getPost(int id) {
		return postRepository.getPost(id);
	}
	
	public List<PostVo> getPostList(int id) {
		List<PostVo> postVo = postRepository.findPostList(id);
		return postVo;
	}
	
	public PostVo getLastPost(String blogId, int categoryId) {
		return postRepository.getLastPost(blogId, categoryId);
	}
	

}
