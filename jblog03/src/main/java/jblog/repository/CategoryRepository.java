package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	private SqlSession sqlSession;

	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<CategoryVo> findCategoryList(String id) {
		return sqlSession.selectList("category.detail", id);
	}
	
	public List<CategoryVo> findCategories(String id) {
		return sqlSession.selectList("category.categories", id);
	}
	

	public int insertCategory(CategoryVo vo) {
		return sqlSession.insert("category.insert", vo);
	}

	public void deleteById(int id) {
		sqlSession.delete("category.deleteById", id);
	}

	public CategoryVo getDefaultCategoryIdAndName(String id) {
		return sqlSession.selectOne("category.getDefaultCategoryIdAndName", id);
	}
}
