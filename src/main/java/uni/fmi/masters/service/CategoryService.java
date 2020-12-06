package uni.fmi.masters.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.CategoryBean;
import uni.fmi.masters.repository.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<CategoryBean> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public CategoryBean getCategoryById(Integer id) {
		return categoryRepository.findById(id).get();
	}
	
	public CategoryBean createOrSaveCategory(CategoryBean category) {
		return categoryRepository.save(category);
	}
	
	public CategoryBean updateCategory(CategoryBean updatedCategory, Integer id) {
		return categoryRepository.findById(id).map(category -> {
			category.setName(updatedCategory.getName());
			
			return categoryRepository.save(category);
		}).orElseGet(() -> {
			updatedCategory.setId(id);
			
			return categoryRepository.save(updatedCategory);
		});
	}
	
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	@PostConstruct
	public void generateTestCategoryData() {
		if (categoryRepository.count() == 0) {
			CategoryBean category = new CategoryBean("First category");
			CategoryBean category1 = new CategoryBean("Second category");
			
			categoryRepository.save(category);
			categoryRepository.save(category1);
		}
	}
}
