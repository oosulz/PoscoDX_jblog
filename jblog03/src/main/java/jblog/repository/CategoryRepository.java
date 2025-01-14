package jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	private DataSource dataSource;

	private SqlSession sqlSession;

	public CategoryRepository(DataSource dataSource, SqlSession sqlSession) {
		this.dataSource = dataSource;
		this.sqlSession = sqlSession;
	}

	public int insert(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);
	}
}
