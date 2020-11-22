package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.fmi.masters.bean.MemoryBean;

public interface MemoryRepository extends JpaRepository<MemoryBean, Integer> {

}
