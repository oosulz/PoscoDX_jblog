package jblog.repository;

import java.util.Map;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import jblog.vo.UserVo;

@Repository
public class UserRepository {

	private SqlSession sqlSession;

	public UserRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;

	}

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPassword(String id, String password) {
		return sqlSession.selectOne("user.findByIdAndPassword", Map.of("id", id, "password", password));
	}

	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}

	public int updateUser(UserVo vo) {
		return sqlSession.update("user.update");
	}

}
