package uni.fmi.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.MemoryBean;
import uni.fmi.masters.repository.MemoryRepository;

@Service
public class MemoryService {
	private MemoryRepository memoryRepository;
	
	public MemoryService(MemoryRepository memoryRepository) {
		this.memoryRepository = memoryRepository;
	}
	
	public List<MemoryBean> getAllMemories() {
		return memoryRepository.findAll();
	}
	
	public MemoryBean getMemoryById(Long id) {
		return memoryRepository.findById(id).get();
	}
	
	public MemoryBean createOrSaveMemory(MemoryBean memory) {
		return memoryRepository.save(memory);
	}
	
	public MemoryBean updateMemory(MemoryBean updatedMemory, Long id) {
		return memoryRepository.findById(id).map(memory -> {
			memory.setTitle(updatedMemory.getTitle());
			memory.setDescription(updatedMemory.getDescription());
			memory.setCategory(updatedMemory.getCategory());
			memory.setUser(updatedMemory.getUser());
			
			return memoryRepository.save(memory);
		}).orElseGet(() -> {
			updatedMemory.setId(id);
			
			return memoryRepository.save(updatedMemory);
		});
	}
	
	public void deleteMemory(Long id) {
		memoryRepository.deleteById(id);
	}
}
