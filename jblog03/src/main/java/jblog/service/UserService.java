package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}
	

	public UserVo getUser(String id, String password) {
		UserVo vo = userRepository.findByIdAndPassword(id,password);
		return vo;
		
	}
	
	public UserVo getUser(Long id) {
		UserVo vo = userRepository.findById(id);
		//System.out.println(vo.toString());
		return vo;
		
	}
	public void update(UserVo userVo) {
		userRepository.updateUser(userVo);
	}

	public UserVo getUser(String email) {
		return userRepository.findByEmail(email);
	}
}
