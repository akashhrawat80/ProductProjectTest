package com.productproject;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productproject.controller.ProductsRestController;
import com.productproject.dao.ProductRepository;
import com.productproject.entities.Category;
import com.productproject.entities.Product;
import com.productproject.entities.Product.Type;

@RunWith(MockitoJUnitRunner.class)
public class ProductsRestResourceTest {

	
	@InjectMocks
	ProductsRestController productsRestController;
	
	@Mock
	private ProductRepository productRepository;
	
	//@Autowired
	//private WebApplicationContext webApplicationContext; 
	ObjectMapper objectMapper=new ObjectMapper();
	com.fasterxml.jackson.databind.ObjectWriter objectWriter=objectMapper.writer();
	
	Product p1=new Product(6L,"new one",Type.valueOf("CLOTHING"),new Category(5,"new category"));
	Product p2=new Product(7L,"new two",Type.valueOf("CLOTHING"),new Category(5,"new category"));
	Product p3=new Product(8L,"new three",Type.valueOf("CLOTHING"),new Category(5,"new category"));
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(productsRestController).build();
		
	}
	@Test
	public void testAll() throws Exception {
		
		List<Product> records=new ArrayList<>();
		records.add(p1);
		records.add(p2);
		records.add(p3);
		Mockito.when(productRepository.findAll()).thenReturn(records);
		mockMvc.perform(
		MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON)
	).andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].name",Matchers.is("new three")));
	}
}
