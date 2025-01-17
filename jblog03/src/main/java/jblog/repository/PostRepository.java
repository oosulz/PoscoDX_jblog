package jblog.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;


@Repository
public class PostRepository {

	private SqlSession sqlSession;

	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insertPost(PostVo postVo) {
		return sqlSession.insert("post.insertPost",postVo);
		
	}

	public PostVo getPost(int id) {
		return sqlSession.selectOne("post.findById", id);	
	}	
	
	
	public PostVo getLastPost(String id, int categoryId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("blogId", id);
	    params.put("categoryId", categoryId);
	    return sqlSession.selectOne("post.findLastById", params);
	}
	
	
	public PostVo getDefaultLastById(String id) {
		return sqlSession.selectOne("post.findDefaultLastById", id);	
	}
	

	public List<PostVo> findPostList(int id) {
		return sqlSession.selectList("post.findListById", id);	
	}

}
