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
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository iProductRepository;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<ProductResponse> getProducts() {

		List<Product> productList = iProductRepository.findAll();

		log.info("Mapping Product list to ProductResponse list");

		List<ProductResponse> productResponseList = productList.parallelStream()
				.map(ProductService::convertToProductResponse).collect(Collectors.toList());

		log.info("Mapped Product list to ProductResponse list");

		return productResponseList;
	}

	@Override
	public void createProduct(List<ProductRequest> productRequestList) {

		log.info("Mapping ProductResponse list to Product list");

		List<Product> productList = productRequestList.parallelStream().map(ProductService::convertToProduct)
				.collect(Collectors.toList());

		log.info("Mapped ProductResponse list to Product list");

		iProductRepository.saveAll(productList);

	}

	private static ProductResponse convertToProductResponse(Product product) {
		return objectMapper.convertValue(product, ProductResponse.class);
	}

	private static Product convertToProduct(ProductRequest productRequest) {
		return objectMapper.convertValue(productRequest, Product.class);
	}

}
