package com.mindtree.orderitem.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.orderitem.entity.Product;
import com.mindtree.orderitem.repository.IProductRepository;
import com.mindtree.orderitem.util.ProductRequest;
import com.mindtree.orderitem.util.ProductResponse;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository iProductRepository;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<ProductResponse> getProducts() {

		List<Product> productList = iProductRepository.findAll();

		List<ProductResponse> productResponseList = productList.parallelStream()
				.map(ProductService::convertToProductResponse).collect(Collectors.toList());

		return productResponseList;
	}

	@Override
	public void createProduct(List<ProductRequest> productRequestList) {

		List<Product> productList = productRequestList.parallelStream().map(ProductService::convertToProduct)
				.collect(Collectors.toList());

		iProductRepository.saveAll(productList);

	}

	private static ProductResponse convertToProductResponse(Product product) {
		return objectMapper.convertValue(product, ProductResponse.class);
	}

	private static Product convertToProduct(ProductRequest productRequest) {
		return objectMapper.convertValue(productRequest, Product.class);
	}

}
