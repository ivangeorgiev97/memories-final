package uni.fmi.masters.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/users")
	public Collection<UserBean> getAllUsers() {
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
	UserBean updateUser(@RequestBody UserBean updatedUser, @PathVariable Long id, HttpSession session) {
		UserBean loggedInUser = (UserBean) session.getAttribute("user");
		
		return userService.updateUser(updatedUser, id, loggedInUser);
	}
	
	// TODO - Refactor delete too
	@DeleteMapping("/users/{id}")
	ResponseEntity<Boolean> deleteUser(@PathVariable Long id, HttpSession session) {
		UserBean loggedInUser = (UserBean) session.getAttribute("user");
		
		if (loggedInUser != null) {
			boolean userDeleted = userService.deleteUserById(id, loggedInUser);
			 
			if (userDeleted) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
			}
		}
		
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}
}
