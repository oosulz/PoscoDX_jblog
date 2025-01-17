package jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getCategoryList(String id) {
		List<CategoryVo> categoryVo = categoryRepository.findCategoryList(id);
		return categoryVo;
	}
	
	public Optional<CategoryVo> getDefaultCategoryIdAndName(String blogId) {
	    CategoryVo categoryVo = categoryRepository.getDefaultCategoryIdAndName(blogId);
	    return Optional.ofNullable(categoryVo);

	}
	
	@Transactional
	public void addCategory(CategoryVo categoryVo) {
		categoryRepository.insertCategory(categoryVo);
	}
	
	@Transactional
	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}
	
	public List<CategoryVo> getCategories(String id) {
		List<CategoryVo> categoryVo = categoryRepository.findCategories(id);
		return categoryVo;
	}

}
