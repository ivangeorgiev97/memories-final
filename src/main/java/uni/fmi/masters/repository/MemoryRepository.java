package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.MemoryBean;

@Repository
public interface MemoryRepository extends JpaRepository<MemoryBean, Long> {

}
