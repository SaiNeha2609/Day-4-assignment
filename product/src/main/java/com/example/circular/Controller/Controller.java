package com.example.circular.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.circular.Model.Product;
import com.example.circular.Repo.ProductRepository;
import com.example.circular.Service.ProductService;


@RestController
public class Controller {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepo;
	
	@PostMapping("/createProduct")
	  public ResponseEntity<?> createProduct(@RequestBody Product product) {
	    if(product.getQuantity() > 100) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity should be less than or equal to 100");
	    }
	    
	    if(!Arrays.asList("Dairy", "Pharma", "Produce").contains(product.getCategory())) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category");
	    }
	    
	    if(product.getName().isEmpty() || !product.getName().matches("^[a-zA-Z]+$")) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name should start with an alphabet");
	    }
	    
	    if(product.getPrice() <= 0) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price should be greater than zero");
	    }
	    
	    // Check if product expiry date is valid
	    
	    
	    return ResponseEntity.ok(productService.save(product));
	  }
	
	 @GetMapping("/getAll")
     public List<Product> getProducts() {
        return productService.getAllProducts();

    }
	
//	 @PostMapping("/addProduct")
//     public String addAccount(@RequestBody Product product)  {
//         Product productList=productService.addProduct(product);
//         return "Product added successfully";
//
//     }
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	    Product existingProduct = productRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	    
	    // Validate updates
	    if(existingProduct.getId() != id) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update product ID");
	    }
	    
	    if(product.getQuantity() > 100) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity should be less than or equal to 100");
	    }
	    
	    if(!Arrays.asList("Dairy", "Pharma", "Produce").contains(product.getCategory())) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category");
	    }
	    
	    if(product.getName().isEmpty() || !product.getName().matches("^[a-zA-Z]+$")) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name should start with an alphabet");
	    }
	    
	    if(product.getPrice() <= 0) {
	      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product price should be greater than zero");
	    }
	    
	    // Update product
	    existingProduct.setQuantity(product.getQuantity());
	    existingProduct.setCategory(product.getCategory());
	    existingProduct.setName(product.getName());
	    existingProduct.setPrice(product.getPrice());
	    return ResponseEntity.ok(productService.save(existingProduct));
	  }
	 
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<?> deleteCompany(@PathVariable long id) {
		    try {
		      productService.deleteByID(id);
		      return new ResponseEntity<>("delete successfully",HttpStatus.OK);
		    } catch (Exception e) {
		      return ResponseEntity.badRequest().body(null);
		    }
		  }

}
