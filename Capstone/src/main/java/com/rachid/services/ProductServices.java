package com.rachid.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rachid.model.Product;
import com.rachid.repository.ProductRepository;

//Create,Read, Update, Delete
@Service
public class ProductServices {
	
	ProductRepository productRepo;
	
	public Product saveProduct(Product product)
	{
		return productRepo.save(product);
	}
	
	public List<Product> getAllProducts()
	{
		return productRepo.findAll();
	}
	
	public Product findProductById(long id) throws Exception {
	    Optional<Product> prod = productRepo.findById(id);
	    if(prod.isPresent()) {
	      return prod.get();
	    }else
	    {
	      throw new Exception("Not found");
	    }
	  }
	
	  public Product updateProduct(Product product, long id) throws Exception {
		    
		    Product prod = productRepo.findById(id).get();
		    if(prod.getProductId()!=0) {
		      prod.setProductName(product.getProductName());
		      prod.setProductPrice(product.getProductPrice());
		      prod.setProductImg(product.getProductImg());
		      prod.setProductStock(product.getProductStock());
		    }
		    else
		    {
		      throw new Exception("Not found");
		    }
		    productRepo.save(prod);
		    return prod;
		  }
	  
	  public void deleteProduct(long id) {
		    productRepo.deleteById(id); 
		  }
}
