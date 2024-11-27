package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/** CarRepository
 * By extending CrudRepository and using the Spring Data Rest library
 * this repository will automatically be found and made available for
 * CRUD operations.
 * 
 * By adding annotation @RepositoryRestResource the methods we defined
 * in this interface will automatically be found and made available for 
 * REST calls.
 */


@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
	
	List<Car> findByBrandAndModelOrderByBrandAsc(@Param("brand")String brand, @Param("model")String model);
	
	List<Car> findByModel(@Param("model")String model);
	
	List<Car> findByBrand(@Param("brand")String brand);
	
	List<Car> findByColor(@Param("color")String color);

}


