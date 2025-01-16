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

	/*
	 * public int plusHit(Long id) { return sqlSession.update("board.plusHit", id);
	 * }
	 * 
	 * private int getMaxGroupNo() { return
	 * sqlSession.selectOne("board.getMaxGroupNo"); }
	 * 
	 * 
	 * 
	 * public PostVo findById(Long boardId) { return
	 * sqlSession.selectOne("board.findById", boardId); }
	 * 
	 * public int deleteById(Long id) { return sqlSession.delete("board.deleteById",
	 * id); }
	 * 
	 * public int insert(BoardVo vo) { if (vo.getgNo() == 0) {
	 * vo.setgNo(getMaxGroupNo() + 1); vo.setoNo(1); vo.setDepth(0);
	 * 
	 * } else { sqlSession.update("board.updateOrderNo", vo); }
	 * 
	 * return sqlSession.insert("board.insertBoard", vo); }
	 * 
	 * public int update(BoardVo vo) { return sqlSession.update("board.update", vo);
	 * }
	 * 
	 * public List<BoardVo> getList(int currentPage, int pageSize) { Map<String,
	 * Integer> map = new HashMap<>(); map.put("currentpage", (currentPage - 1) *
	 * pageSize); map.put("pagesize", pageSize);
	 * 
	 * return sqlSession.selectList("board.findByCurrentPageAndPageSize", map); }
	 */
}
