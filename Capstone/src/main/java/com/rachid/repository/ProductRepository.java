package com.rachid.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachid.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
