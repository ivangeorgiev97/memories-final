package uni.fmi.masters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.bean.CategoryBean;
import uni.fmi.masters.service.CategoryService;

@RestController
public class CategoryController {
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping(value = "/categories")
	public List<CategoryBean> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/categories/{id}")
	CategoryBean getCategoryById(@PathVariable Integer id) {
		return categoryService.getCategoryById(id);
	}
	
	@PostMapping("/categories")
	CategoryBean createOrSaveCategory(@RequestBody CategoryBean category) {
		return categoryService.createOrSaveCategory(category);
	}
	
	@PutMapping("/categories/{id}")
	CategoryBean updateCategory(@RequestBody CategoryBean updatedCategory, @PathVariable Integer id) {
		return categoryService.updateCategory(updatedCategory, id);
	}
	
	@DeleteMapping("/categories/{id}")
	void deleteCatgory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
	}
	
}
