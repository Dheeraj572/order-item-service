package com.mindtree.orderitem.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

	private Long id;
	private String productName;
	private Long productCode;
	private Long quantity;
}
