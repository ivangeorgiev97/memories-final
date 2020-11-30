package uni.fmi.masters.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.UserService;

public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/useries")
	public Collection<UserBean> getAlluseries() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	UserBean getuseryById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/users")
	UserBean createOrSaveUser(@RequestBody UserBean user) {
		return userService.createOrSaveUser(user);
	}
	
	@PutMapping("/users/{id}")
	UserBean updateusery(@RequestBody UserBean updatedUser, @PathVariable Long id) {
		return userService.updateUser(updatedUser, id);
	}
	
	@DeleteMapping("/users/{id}")
	void deleteCatgory(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
}
