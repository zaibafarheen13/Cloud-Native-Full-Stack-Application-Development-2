package com.lab5.my_lab5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final List<Product> products = new ArrayList<>();
	
	/*
	 * OR
	 * 
	 * @PostMapping public ResponseEntity<?> addProduct(@Valid @RequestBody Product
	 * product,BindingResult result) {
	 * 
	 * List<String> displayErrors = new ArrayList<String>();
	 * 
	 * if (result.hasErrors()) { List<FieldError> errors = result.getFieldErrors();
	 * for(FieldError err:errors) { displayErrors.add(err.getField() + ": " +
	 * err.getDefaultMessage()); System.out.println(displayErrors); } return
	 * ResponseEntity.badRequest().body(displayErrors); } products.add(product);
	 * return ResponseEntity.status(HttpStatus.CREATED).body(product); }
	 */
	
	@PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult result) {
        List<String> displayErrors = getErrors(result);

        if (!displayErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(displayErrors);
        }

        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

	@GetMapping
	public List<Product> getProduct(){
		return products;
	}
	
	@PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @Valid @RequestBody Product updatedProduct, BindingResult result) {
        Product existingProduct = findProductById(productId);

        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        List<String> displayErrors = getErrors(result);

        if (!displayErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(displayErrors);
        }

        // Update the existing product
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return ResponseEntity.ok(existingProduct);
    }
	
	// Helper method to extract field errors from BindingResult
    private List<String> getErrors(BindingResult result) {
        List<String> displayErrors = new ArrayList<>();
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                displayErrors.add(error.getField() + ": " + error.getDefaultMessage());
            }
        }
        return displayErrors;
    }

    // Helper method to find a product by ID
    private Product findProductById(Long productId) {
    	for(Product p:products) {
        	if(p.getId().equals(productId)) {
        		return p;
        	}
        }
		return null;
    }

}