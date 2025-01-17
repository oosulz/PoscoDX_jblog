package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.service.UserService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{path1}", "/{path1}/{path2}" })
	public String index(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2, Model model, HttpServletRequest request) {
		
		// 유저 있는지 처리하기
		UserVo userVo = userService.getUser(id);
		
		if (userVo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		
		// 블로그 정보 불러오기
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.getCategories(id);
		
		model.addAttribute("categoryList", categoryVo);
		model.addAttribute("currentId", id);
		model.addAttribute("blogVo", blogVo);
		
		
		// 카테고리,포스트 처리
		Long categoryId = 0L;
		Long postId = 0L;
		
		if (path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}
		
		// 미지정 카테고리 아이디 + '미지정' 불러오기
		Optional<CategoryVo> categoryOptional = categoryService.getDefaultCategoryIdAndName(id);
		System.out.println("categoryOptional: " + categoryOptional);
		
		if (categoryId == 0L) {
			CategoryVo categoryIdAndName = categoryOptional.orElseThrow(() -> 
		        new IllegalStateException("Default category not found for blogId: " + id)
		    );
		    categoryId = (long) categoryIdAndName.getId();
		}
		
		// 게시물 리스트 불러오기 
		List<PostVo> postVo = postService.getPostList(categoryId.intValue());
		model.addAttribute("postList", postVo);
		
		String name = categoryOptional.map(CategoryVo::getName).orElse("기본값");
		
		// 게시물 불러오기
		PostVo latestPost = null;
		
		if (name.equals("미지정") && postId == 0L) {
			latestPost = postService.getLastPost(id,categoryId.intValue());
		} else {
			latestPost = postService.getPost(postId.intValue());
		}
		
		model.addAttribute("currentCategoryId", categoryId);
		
		if (latestPost == null) {
		    model.addAttribute("post", null);
		    model.addAttribute("currentPostId", null);
		} else {
		    model.addAttribute("post", latestPost);
		    model.addAttribute("currentPostId", latestPost.getId());
		}
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping({ "/admin/category" })
	public String adminCategory(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.getCategoryList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("currentId", id);
		model.addAttribute("categoryList", categoryVo);
		return "blog/category";
	}
	
	@Auth
	@RequestMapping(value ="/admin/category", method=RequestMethod.POST)
	public String adminCategoryAdd(
								@PathVariable("id") String id,
								@RequestParam("name") String name, 
								@RequestParam(value="desc", defaultValue="") String desc,
								Model model) {
			        
		CategoryVo categoryVo = new CategoryVo();
        categoryVo.setName(name);
        categoryVo.setDescription(desc);
        categoryVo.setBlogId(id);
        categoryVo.setTotalCount(0);
		categoryService.addCategory(categoryVo);
		return "redirect:/" +  id + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value ="/admin/category/delete", method=RequestMethod.POST)
	public String adminCategoryDelete(@PathVariable("id") String id,
									  @RequestParam("blogid") int blogId) {
		categoryService.deleteCategory(blogId);
		return "redirect:/" +  id + "/admin/category";
	}
	
	
	
	@Auth
	@RequestMapping({ "/admin/write" })
	public String adminWrite(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryVo = categoryService.getCategories(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("currentId", id);
		model.addAttribute("categoryList", categoryVo);
		return "blog/write";
	}
	
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWritePost(@PathVariable("id") String id,
								 @RequestParam("category") int categoryId,
								 @RequestParam("title") String title,
								 @RequestParam("content") String content) {
		PostVo postVo = new PostVo();
		postVo.setTitle(title);
		postVo.setContents(content);
		postVo.setCategoryId(categoryId);
		postService.insertPost(postVo);
		return "redirect:/" + id + "/admin/write";
	}
	
	@Auth
	@RequestMapping({ "/admin" })
	public String adminDefault(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("currentId", id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin";
	}
	
	@RequestMapping(value = "/admin",method = RequestMethod.POST)
	public String update(
		@PathVariable("id") String id,
		@RequestParam("title") String title, 
		@RequestParam("logo-file") MultipartFile file) {
		
		BlogVo blogVo = blogService.getBlog(id);
		blogVo.setTitle(title);
	    
	    if (file != null && !file.isEmpty()) {
	        String profilePath = fileUploadService.restore(file);
	        if (profilePath != null) {
	        	blogVo.setProfile(profilePath);
	        }
	    }
	    
	    blogService.update(blogVo);
	    return "redirect:/" + id + "/admin";

	}

}
