package com.rachid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rachid.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
}
