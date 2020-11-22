package uni.fmi.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.fmi.masters.bean.CategoryBean;

public interface CategoryRepository extends JpaRepository<CategoryBean, Integer> {

}
