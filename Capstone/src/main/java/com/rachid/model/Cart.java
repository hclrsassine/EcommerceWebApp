package com.rachid.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	private Long productId;
	private String productName;
	private int quantity;
	
	public Cart(Long productId, int quantity)
	{
		this.productId = productId;
		this.quantity = quantity;
	}
}
