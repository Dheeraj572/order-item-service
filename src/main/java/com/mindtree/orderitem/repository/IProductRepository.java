package com.mindtree.orderitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.orderitem.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
