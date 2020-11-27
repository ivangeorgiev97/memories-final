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
import uni.fmi.masters.repository.CategoryRepository;

@RestController
public class CategoryController {
	private CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping(value = "/categories")
	public List<CategoryBean> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/categories/{id}")
	CategoryBean getCategoryById(@PathVariable Integer id) {
		return categoryRepository.findById(id).get();
	}
	
	@PostMapping("/categories")
	CategoryBean createOrSaveCategory(@RequestBody CategoryBean category) {
		return categoryRepository.save(category);
	}
	
	@PutMapping("/categories/{id}")
	CategoryBean updateCategory(@RequestBody CategoryBean updatedCategory, @PathVariable Integer id) {
		return categoryRepository.findById(id).map(category -> {
			category.setName(updatedCategory.getName());
			
			return categoryRepository.save(category);
		}).orElseGet(() -> {
			updatedCategory.setId(id);
			
			return categoryRepository.save(updatedCategory);
		});
	}
	
	@DeleteMapping("/categories/{id}")
	void deleteCatgory(@PathVariable Integer id) {
		categoryRepository.deleteById(id);
	}
	
}
