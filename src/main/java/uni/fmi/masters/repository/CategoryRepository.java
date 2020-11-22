package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.bean.CategoryBean;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryBean, Integer> {

}
