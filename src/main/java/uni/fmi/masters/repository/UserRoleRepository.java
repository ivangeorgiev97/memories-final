package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.fmi.masters.bean.UserRoleBean;

public interface UserRoleRepository extends JpaRepository<UserRoleBean, Integer> {

}
