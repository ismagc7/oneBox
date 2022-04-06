package com.example.onebox.persistence.repository;

import com.example.onebox.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query(nativeQuery = true, value="SELECT case when (count(*) > 0)  then true else false end "
			+ "FROM PRODUCT WHERE DESCRIPTION = :description")
	 Boolean findByDescriptionIsLike(String description);

}

