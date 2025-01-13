package jblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.service.UserService;
import jblog.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println("LoginInterceptor triggered for path: " + request.getRequestURI());
		System.out.println("id :"+ id+" || password :"+ password);
		
		UserVo authUser = userService.getUser(id, password); 
		if(authUser == null) {
			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		
		System.out.println("[authUser] : " +authUser);
		
		return false;
	}

	

}
