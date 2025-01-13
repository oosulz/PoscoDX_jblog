package jblog.repository;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import org.apache.ibatis.session.SqlSession;

import jblog.vo.UserVo;

@Repository
public class UserRepository {

	private DataSource dataSource;

	private SqlSession sqlSession;

	public UserRepository(DataSource dataSource, SqlSession sqlSession) {
		this.dataSource = dataSource;
		this.sqlSession = sqlSession;
	}

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
	}

	public UserVo findById(Long userId) {
		return sqlSession.selectOne("user.findById", userId);
	}

	public int updateUser(UserVo vo) {
		return sqlSession.update("user.update");
	}

	public UserVo findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
		
	}

}
