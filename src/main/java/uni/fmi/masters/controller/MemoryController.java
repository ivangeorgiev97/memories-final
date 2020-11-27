package uni.fmi.masters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.bean.MemoryBean;
import uni.fmi.masters.repository.MemoryRepository;

@RestController
public class MemoryController {
	private MemoryRepository memoryRepository;
	
	public MemoryController(MemoryRepository memoryRepository) {
		this.memoryRepository = memoryRepository;
	}
	
	@GetMapping(value = "/memories")
	public List<MemoryBean> getAllmemories() {
		return memoryRepository.findAll();
	}
	
	@GetMapping("/memories/{id}")
	MemoryBean getmemoryById(@PathVariable Integer id) {
		return memoryRepository.findById(id).get();
	}
	
	@PostMapping("/memories")
	MemoryBean createOrSavememory(@RequestBody MemoryBean memory) {
		return memoryRepository.save(memory);
	}
	
	@PutMapping("/memories/{id}")
	MemoryBean updatememory(@RequestBody MemoryBean updatedMemory, @PathVariable Integer id) {
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
	
	@DeleteMapping("/memories/{id}")
	void deleteCatgory(@PathVariable Integer id) {
		memoryRepository.deleteById(id);
	}
	
}
