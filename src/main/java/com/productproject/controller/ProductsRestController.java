package com.productproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productproject.service.PriceCalculator;
import com.productproject.entities.Product;
import com.productproject.dao.ProductRepository;
//import com.productproject.service.ProductService;

@RestController
@RequestMapping("/rest/product")
public class ProductsRestController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		Iterable<Product> productsIterable = productRepository.findAll();
		List<Product> products = new ArrayList<>();
		productsIterable.forEach(products::add); // add elements form Iterable to the list.
		products.forEach(this::setProduct); //populate product
		if (!products.isEmpty()) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	private void setProduct(Product product) {
		PriceCalculator.getPopulatedProduct(product);
		System.out.println(product);
	}

	
	@GetMapping("/price/{id}")
	public ResponseEntity<Double> getPrice(@PathVariable("id") Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		Product product = null;
		if (opProduct.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			product = opProduct.get();
			setProduct(product);
			return new ResponseEntity<>(product.getFinalPrice(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/create/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product prod= productRepository.save(product);
		if(prod==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(prod,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		Product product = null;
		if (opProduct.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			product = opProduct.get();
			productRepository.delete(product);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails){
		Optional<Product> opProduct = productRepository.findById(id);
		Product updateProduct = null;
		if (opProduct.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			updateProduct = opProduct.get();
			updateProduct.setBasePrice(productDetails.getBasePrice());
			updateProduct.setCategory(productDetails.getCategory());
			updateProduct.setName(productDetails.getName());
			updateProduct.setType(productDetails.getType());
		
			productRepository.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		}
	}
}
