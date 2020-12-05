package uni.fmi.masters.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.controller.LoginController;
import uni.fmi.masters.repository.UserRepository;
import uni.fmi.masters.repository.UserRoleRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	public UserBean getUserByUsernameAndPassword(String username, String password) {
		return userRepository.getUserByUsernameAndPassword(username, password);
	}

	public UserBean getByUsername(String username) {
		return userRepository.getByUsername(username);
	}
	
	public UserBean getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	public Collection<UserBean> findAll() {
		return userRepository.findAll();
	}

	@PostConstruct
	public void generateTestUserData() {
		if (userRepository.count() == 0) {
			UserBean user0 = new UserBean("firstuser", LoginController.hashPassword("firstuser"), "example@example.com");
			UserBean user1 = new UserBean("seconduser", LoginController.hashPassword("seconduser"), "example1@example.com");
			UserBean user2 = new UserBean("thirduser", LoginController.hashPassword("thirduser"), "example2@example.com");
			userRepository.save(user0);
			userRepository.save(user1);
			userRepository.save(user2);
		}
	}

	public void removeUser(UserBean selectedUser) {
		userRepository.delete(selectedUser);
	}
	
	public boolean deleteUserById(Long id, UserBean loggedInUser) {
		Optional<UserBean> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
			
			UserBean user = optionalUser.get();
			
			if (user.getId() == loggedInUser.getId()) {
				userRepository.deleteById(id);
				
				return true;
			} else {
				return false;
			}
		
		}
		
		return false;
	}
	
	public Collection<UserBean> getByUsernameContaining(final String partOfUsername) {
		if (null == partOfUsername || partOfUsername.isEmpty()) {
			return findAll();
		} else {
			return getByUsernameContaining(partOfUsername);
		}
	}
	
	public void saveUser(final UserBean selectedUser) {
		userRepository.save(selectedUser);
	}
	
	public UserBean createOrSaveUser(UserBean user) {
		user.setPassword(LoginController.hashPassword(user.getPassword()));
		// user.setRoles(new HashSet<>(userRoleRepository.findAll()));
		
		return userRepository.save(user);
	}
	
	public UserBean updateUser(UserBean updatedUser, Long id, UserBean loggedInUser) {
		if (loggedInUser != null) {
			return userRepository.findById(id).map(user -> {
				if (user.getId() == loggedInUser.getId()) {
					user.setUsername(updatedUser.getUsername());
					user.setEmail(updatedUser.getEmail());
					// This can be discussed
					user.setPassword(LoginController.hashPassword(updatedUser.getPassword()));
					// Maybe roles too
					// user.setRoles(new HashSet<>(userRoleRepository.findAll()));
					
					return userRepository.save(user);
				}
				
				return null;
			}).orElseGet(() -> {
				updatedUser.setId(id);
				
				return userRepository.save(updatedUser);
			});
		}
		
		return null;
	}

}
