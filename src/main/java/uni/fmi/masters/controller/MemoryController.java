package uni.fmi.masters.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.bean.MemoryBean;
import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.MemoryService;

@RestController
public class MemoryController {
	private MemoryService memoryService;
	
	public MemoryController(MemoryService memoryService) {
		this.memoryService = memoryService;
	}
	
	@GetMapping(value = "/memories")
	public List<MemoryBean> getAllMemories() {
		try {
			return memoryService.getAllMemories();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("/memories/{id}")
	MemoryBean getMemoryById(@PathVariable Long id) {
		return memoryService.getMemoryById(id);
	}
	
	@PostMapping("/memories")
	MemoryBean createOrSaveMemory(@RequestParam(value = "title")String title, @RequestParam(value = "description") String description,
			@RequestParam(value="categoryId")Integer categoryId, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("user");
		
		return memoryService.createOrSaveMemory(title, description, categoryId, user);
	}
	
	@PutMapping("/memories/{id}")
	MemoryBean updatememory(@RequestParam(value = "title")String title, @RequestParam(value = "description") String description,
			@RequestParam(value="categoryId")Integer categoryId, @PathVariable Long id, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("user");
		
		return memoryService.updateMemory(title, description, id, categoryId, user);
	}
	
	@DeleteMapping("/memories/{id}")
	void deleteMemory(@PathVariable Long id) {
		memoryService.deleteMemory(id);
	}
	
}
