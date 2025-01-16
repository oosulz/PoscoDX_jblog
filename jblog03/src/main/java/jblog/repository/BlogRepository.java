package jblog.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Repository
public class BlogRepository {

	private SqlSession sqlSession;

	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(BlogVo vo) {
		return sqlSession.insert("blog.insert", vo);
	}
	
	public BlogVo findUserBlog(String id) {
		return sqlSession.selectOne("blog.findUserBlog", id);
	}
	
	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}

}
