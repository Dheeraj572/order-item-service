package com.mindtree.orderitem.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.orderitem.service.IProductService;
import com.mindtree.orderitem.util.ProductRequest;
import com.mindtree.orderitem.util.ProductResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("products")
@CrossOrigin
@Log4j2
public class ProductController {
	
	@Autowired
	private IProductService iProductService;
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Create products")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Products Created"),
			@ApiResponse(code = 400, message = "Invalid products") })
	@PostMapping
	public void createProduct(@RequestBody List<ProductRequest> productRequestList) {
		
		log.info("Creating products-----");
		
		iProductService.createProduct(productRequestList);
		
		log.info("Products created-----");
	}

	@ApiOperation(value = "Get all products")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retreieved all products", response = ProductResponse.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content") })
	@GetMapping
	public ResponseEntity<?> getProducts(){
		
		log.info("Retrieving products-----");
		
		List<ProductResponse> productResponseList = iProductService.getProducts();
		
		if(Optional.ofNullable(productResponseList).isPresent() && Optional.ofNullable(productResponseList).get().isEmpty()) {
			
			log.info("No Content-----");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		log.info("Retrieved products-----");
		return new ResponseEntity<>(productResponseList, HttpStatus.OK);
	}
}
