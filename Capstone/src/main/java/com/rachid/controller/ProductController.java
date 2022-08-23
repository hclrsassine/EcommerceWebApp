package com.rachid.controller;

import java.util.List;

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

import com.rachid.model.Product;
import com.rachid.services.ProductServices;

@RestController
@RequestMapping("/products")
public class ProductController {
	private ProductServices productServices;
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product p)
	{
		return new ResponseEntity<Product>(productServices.saveProduct(p), HttpStatus.CREATED);
	}
	
	  @GetMapping("/products")
	  public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) throws Exception{
	    return new ResponseEntity<Product>(productServices.findProductById(productId),HttpStatus.OK);
	  }
	  
	  @GetMapping("/catalog")
	  public List<Product> viewProducts()
	  {
		  return productServices.getAllProducts();
	  }
	  
	  
	  @PutMapping("/products")
	  public ResponseEntity<Product> updateProduct(@RequestBody Product p,@PathVariable("id") long productId) throws Exception{
	    return new ResponseEntity<Product>(productServices.updateProduct(p, productId), HttpStatus.OK);
	    
	  }
	  
	  @DeleteMapping("/products")
	  public ResponseEntity<String> deleteProduct(@PathVariable("id") long productId){
	    productServices.deleteProduct(productId);
	    return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	  }
}
