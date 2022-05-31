package com.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository
public interface ProductRepository extends JpaRepository<ProductVO, Integer> {
//	@Query("SELECT productvo FROM Productvo productvo WHERE UPPER(productvo.name) LIKE CONCAT('%',UPPER(:name),'%')", native)
//	List<ProductVO> findUsersWithPartOfName(@Param("name") String name);
}
