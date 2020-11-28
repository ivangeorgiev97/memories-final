package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {
	UserBean getUserByUsernameAndPassword(String username, String password);
	
	UserBean getByUsername(String username);
}
