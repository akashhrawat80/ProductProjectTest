package com.productproject.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.productproject.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
