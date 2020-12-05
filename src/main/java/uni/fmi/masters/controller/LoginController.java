package uni.fmi.masters.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import uni.fmi.masters.WebSecurityConfig;
import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.repository.UserRepository;
import uni.fmi.masters.service.UserSecurityService;
import uni.fmi.masters.service.validation.UserValidation;

@RestController
public class LoginController {

	private UserRepository userRepository;
	private WebSecurityConfig webSecurityConfig;
	private UserValidation userValidation;
	private UserSecurityService userSecurityService;
	
	@Autowired
	public LoginController(UserRepository userRepository, WebSecurityConfig webSecurityConfig, UserValidation userValidation, UserSecurityService userSecurityService) {
		this.userRepository = userRepository;
		this.webSecurityConfig = webSecurityConfig;
		this.userValidation = userValidation;
		this.userSecurityService = userSecurityService;
	}
	
	// old way UserBean register
	@PostMapping(path = "/register")
	public UserBean register(@RequestParam(value = "email")String email, @RequestParam(value = "username") String username,
			@RequestParam(value="password")String password, @RequestParam(value="repeatPassword")String repeatPassword) {
		// Validation coming from userValidation can be placed here
		if(password.equals(repeatPassword)) {
			UserBean user = new UserBean(username, hashPassword(password), email);
			
			// old way
			// return userRepository.saveAndFlush(user);
			return userRepository.saveAndFlush(user);
			
			// userSecurityService.loginAutomatically(username, hashPassword(password));
			
			// return "index.html";
		} else {
			return null;
		}
	}
	
	@PostMapping(path = "/login")
	public String login(
			@RequestParam(value = "username")
			String username, 
			@RequestParam(value = "password")
			String password, HttpSession session) {
		
		UserBean user = userRepository.getUserByUsernameAndPassword(username, hashPassword(password));
		
		if (user != null) {
			session.setAttribute("user", user);
			
			try {
				UserDetails userDetails = webSecurityConfig.userDetailsServiceBean().loadUserByUsername(user.getUsername());
			
				if (userDetails != null) {
					Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
				
					SecurityContextHolder.getContext().setAuthentication(auth);
					
					ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				
					HttpSession http = attr.getRequest().getSession(true);
					http.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
				}
				
				// TODO - Add also logout functionality
				
				return "memories.jsp";
			} catch (UsernameNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "error.html";
	}
	
	@GetMapping(path = "/getLoggedInUser")
	public ResponseEntity<Long> getLoggedInUser(HttpSession session) {
		
		UserBean user = (UserBean)session.getAttribute("user");
		
		if (user != null) {
			return new ResponseEntity<>(user.getId(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Long>((long)0, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path = "/logout")
	public ResponseEntity<Boolean> logout(HttpSession session) {
		UserBean user = (UserBean)session.getAttribute("user");
		
		if (user != null) {
			session.invalidate();
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}
		
	public static String hashPassword(String password) {
		StringBuilder result = new StringBuilder();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(password.getBytes());
			
			byte[] bytes = md.digest();
			
			for(int i = 0; i < bytes.length; i++) {
				result.append((char)bytes[i]);
			}			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
	
		return result.toString();
	}
}
