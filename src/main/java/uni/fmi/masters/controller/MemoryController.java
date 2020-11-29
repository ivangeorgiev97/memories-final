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
import uni.fmi.masters.service.MemoryService;

@RestController
public class MemoryController {
	private MemoryService memoryService;
	
	public MemoryController(MemoryService memoryService) {
		this.memoryService = memoryService;
	}
	
	@GetMapping(value = "/memories")
	public List<MemoryBean> getAllMemories() {
		return memoryService.getAllMemories();
	}
	
	@GetMapping("/memories/{id}")
	MemoryBean getMemoryById(@PathVariable Long id) {
		return memoryService.getMemoryById(id);
	}
	
	@PostMapping("/memories")
	MemoryBean createOrSaveMemory(@RequestBody MemoryBean memory) {
		return memoryService.createOrSaveMemory(memory);
	}
	
	@PutMapping("/memories/{id}")
	MemoryBean updatememory(@RequestBody MemoryBean updatedMemory, @PathVariable Long id) {
		return memoryService.updateMemory(updatedMemory, id);
	}
	
	@DeleteMapping("/memories/{id}")
	void deleteMemory(@PathVariable Long id) {
		memoryService.deleteMemory(id);
	}
	
}
