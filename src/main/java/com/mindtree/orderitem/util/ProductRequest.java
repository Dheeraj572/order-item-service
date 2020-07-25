package com.mindtree.orderitem.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {

	private String productName;
	private Long productCode;
	private Long availableQuantity;
	private Float price;
}
