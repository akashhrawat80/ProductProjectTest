package com.productproject;

import java.util.Iterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.productproject.dao.ProductRepository;
import com.productproject.entities.Product;
//import org.springframework.context.ApplicationContext;

//import com.productproject.dao.ProductRepository;

@SpringBootApplication
public class ProductprojectApplication {

	public static void main(String[] args) {
		ApplicationContext c= SpringApplication.run(ProductprojectApplication.class, args);
		ProductRepository p=c.getBean(ProductRepository.class);
		
		Iterable<Product> itr=p.findAll();
		Iterator<Product> iterator=itr.iterator();
		while(iterator.hasNext()) {
			Product product=iterator.next();
			System.out.println(product);
		}

	}
}