package uni.fmi.masters.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<MemoryBean> getAllMemories(@RequestParam Optional<String> sortCriteria, @RequestParam Optional<Integer> categoryId, @RequestParam Optional<Long> userId) {
		try {
			if (sortCriteria.isPresent() && categoryId.isPresent() && userId.isPresent()) {
				return memoryService.getAllMemories(sortCriteria.get(), categoryId.get(), userId.get());
			} else if (sortCriteria.isPresent() && categoryId.isPresent()) {
				return memoryService.getAllMemories(sortCriteria.get(), categoryId.get(), (long)0);
			} else if (sortCriteria.isPresent() && userId.isPresent()) {
				return memoryService.getAllMemories(sortCriteria.get(), 0, userId.get());
			} else if (categoryId.isPresent() && userId.isPresent()) {
				return memoryService.getAllMemories("id", categoryId.get(), userId.get());
			} else if (sortCriteria.isPresent()) {
				return memoryService.getAllMemories(sortCriteria.get(), 0, (long)0);
			} else if (categoryId.isPresent()) {
				return memoryService.getAllMemories("id", categoryId.get(), (long)0);
			} else if (userId.isPresent()) {
				return memoryService.getAllMemories("id", 0, userId.get());
			}
			
			return memoryService.getAllMemories("id", 0 , (long)0);
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
	
	@PutMapping("/memories/{id}/{categoryId}")
	MemoryBean updateMemory(@RequestBody MemoryBean memory, @PathVariable Long id, @PathVariable Integer categoryId, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("user");
		
		return memoryService.updateMemory(memory, id, categoryId, user);
	}
	
	@DeleteMapping("/memories/{id}")
	ResponseEntity<Boolean> deleteMemory(@PathVariable Long id, HttpSession session) {
		// this might go in separate function
		UserBean user = (UserBean) session.getAttribute("user");
		
		if (user != null) {
			boolean memoryDeleted = memoryService.deleteMemory(id, user);
			
			if (memoryDeleted) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
			}
		}
		
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}
	
}
