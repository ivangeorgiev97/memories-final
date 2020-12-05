package uni.fmi.masters.service;

import java.util.List;
import java.util.Optional;

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
				
				return memoryRepository.saveAndFlush(memory);
			}
		}

		return null;
	}
	
	public MemoryBean updateMemory(MemoryBean updatedMemory, Long id, Integer categoryId, UserBean user) {
		if (user != null) {
			return memoryRepository.findById(id).map(memory -> {
				CategoryBean memoryCategory = categoryRepository.findById(categoryId).get();
				
				// this could be refactored
				Optional<MemoryBean> optionalMemory = memoryRepository.findById(id);
				
				if (memoryCategory != null && optionalMemory.isPresent()) {
					MemoryBean finalMemory = optionalMemory.get();
					
					if (finalMemory.getUser().getId() == user.getId()) {
						memory.setTitle(updatedMemory.getTitle());
						memory.setDescription(updatedMemory.getDescription());
						memory.setCategory(memoryCategory);
						memory.setUser(user);
						
						return memoryRepository.save(memory);
					}
					
					return null;
				}
				
				return null;
			}).orElseGet(() -> {
				MemoryBean newMemory = new MemoryBean();
				newMemory.setId(id);
				
				return memoryRepository.save(newMemory);
			});
		}
		
		return null;
	}
	
	public boolean deleteMemory(Long id, UserBean user) {
		Optional<MemoryBean> optionalMemory = memoryRepository.findById(id);
		
		if (optionalMemory.isPresent()) {
			MemoryBean memory = optionalMemory.get();
			
			if (memory.getUser().getId() == user.getId()) {
				memoryRepository.deleteById(id);
				
				return true;
			}
		}
		
		return false;
	}
}
