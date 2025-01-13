package jblog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jblog.vo.UserVo;

@Controller
public class BlogController {
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog(@ModelAttribute UserVo userVo) {
		return "user/blog";
	}
}
