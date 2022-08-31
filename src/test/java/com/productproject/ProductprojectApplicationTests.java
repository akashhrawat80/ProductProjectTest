package com.productproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.productproject.dao.ProductRepository;
import com.productproject.entities.Product;

@SpringBootTest
class ProductprojectApplicationTests {

	@Autowired
	ProductRepository p;
	
	@Test
	@Order(1)
	public void testRead() {
		Optional<Product> opProduct = p.findById(1L);
		Product product= opProduct.get();
		assertNotNull(product);
	}
	
	@Test
	@Order(2)
	public void testReadAll() {
		
		Iterable<Product> productsIterable = p.findAll();
		List<Product> products = new ArrayList<>();
		productsIterable.forEach(products::add);
		assertThat(products).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testSingleProduct() {
		Product product=p.findById(4L).get();
		assertEquals(1200,product.getBasePrice());
		
	}
	
	@Test
	@Order(4)
	public void testUpdate() {
		Product product=p.findById(4L).get();
		product.setBasePrice(100000);
		assertNotEquals(1200,p.findById(4L).get().getBasePrice());
	}
	
	@Test
	@Order(5)
	public void testDelete() {
		p.deleteById(5L);
		assertThat(p.existsById(5L)).isFalse();
	}
}
