package com.foodBox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.foodBox.model.product;

public interface productRepo extends JpaRepository<product, Long>{

	product findByProductName(String catName);
	@Modifying
	@Transactional
	@Query("delete from product p where p.id=:id")
	void deleteByid(long id);
	
	List<product>  findByProductNameContaining(@Param("productName") String keyword);

}
