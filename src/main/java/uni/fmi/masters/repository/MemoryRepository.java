package uni.fmi.masters.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.MemoryBean;

@Repository
public interface MemoryRepository extends JpaRepository<MemoryBean, Long> {
	List<MemoryBean> findByCategoryId(Integer categoryId, Sort sortCriteria);
	List<MemoryBean> findByUserId(Long userId, Sort sortCriteria);
	List<MemoryBean> findByUserIdAndCategoryId(Long userId, Integer categoryId, Sort sortCriteria);
}
