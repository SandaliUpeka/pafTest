package com.crud.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
