package uni.fmi.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.CategoryBean;
import uni.fmi.masters.bean.MemoryBean;
import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.repository.CategoryRepository;
import uni.fmi.masters.repository.MemoryRepository;

@Service
public class MemoryService {
	private MemoryRepository memoryRepository;
	private CategoryRepository categoryRepository;
	
	public MemoryService(MemoryRepository memoryRepository, CategoryRepository categoryRepository) {
		this.memoryRepository = memoryRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<MemoryBean> getAllMemories() {
		return memoryRepository.findAll();
	}
	
	public MemoryBean getMemoryById(Long id) {
		return memoryRepository.findById(id).get();
	}
	
	public MemoryBean createOrSaveMemory(String title, String description, Integer categoryId, UserBean user) {
		if (user != null) {
			MemoryBean memory = new MemoryBean();
			memory.setTitle(title);
			memory.setDescription(description);
			
			CategoryBean memoryCategory = categoryRepository.findById(categoryId).get();
			
			if (memoryCategory != null) {
				memory.setCategory(memoryCategory);
				memory.setUser(user);
				
				return memoryRepository.save(memory);
			}
		}

		return null;
	}
	
	public MemoryBean updateMemory(String title, String description, Long id, Integer categoryId, UserBean user) {
		if (user != null) {
			return memoryRepository.findById(id).map(memory -> {
				CategoryBean memoryCategory = categoryRepository.findById(categoryId).get();
				
				if (memoryCategory != null) {
					memory.setTitle(title);
					memory.setDescription(description);
					memory.setCategory(memoryCategory);
					memory.setUser(user);
					
					return memoryRepository.save(memory);
				}
				
				return null;
			}).orElseGet(() -> {
				MemoryBean updatedMemory = new MemoryBean();
				updatedMemory.setId(id);
				
				return memoryRepository.save(updatedMemory);
			});
		}
		
		return null;
	}
	
	public void deleteMemory(Long id) {
		memoryRepository.deleteById(id);
	}
}
