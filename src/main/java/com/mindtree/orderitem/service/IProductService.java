package com.mindtree.orderitem.service;

import java.util.List;
import com.mindtree.orderitem.util.ProductRequest;
import com.mindtree.orderitem.util.ProductResponse;

public interface IProductService {

	List<ProductResponse> getProducts();
	void createProduct(List<ProductRequest> productRequestList);
}
