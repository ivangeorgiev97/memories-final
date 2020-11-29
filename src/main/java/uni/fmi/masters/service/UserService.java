package uni.fmi.masters.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.controller.LoginController;
import uni.fmi.masters.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserBean getUserByUsernameAndPassword(String username, String password) {
		return userRepository.getUserByUsernameAndPassword(username, password);
	}

	public UserBean getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

	public Collection<UserBean> findAll() {
		return userRepository.findAll();
	}

	@PostConstruct
	public void generateTestUserData() {
		if (userRepository.count() == 0) {
			UserBean user0 = new UserBean("admin", LoginController.hashPassword("admin"), "example@example.com");
			UserBean user1 = new UserBean("user", LoginController.hashPassword("user"), "example1@example.com");
			UserBean user2 = new UserBean("test", LoginController.hashPassword("test"), "example2@example.com");
			userRepository.save(user0);
			userRepository.save(user1);
			userRepository.save(user2);
		}
	}

	public void removeUser(UserBean selectedUser) {
		userRepository.delete(selectedUser);
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

}
