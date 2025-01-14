package jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	private DataSource dataSource;

	private SqlSession sqlSession;

	public BlogRepository(DataSource dataSource, SqlSession sqlSession) {
		this.dataSource = dataSource;
		this.sqlSession = sqlSession;
	}

	public int insert(BlogVo vo) {
		return sqlSession.insert("blog.insert", vo);
	}
}
