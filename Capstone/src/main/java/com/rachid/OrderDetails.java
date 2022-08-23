package com.rachid;

import java.util.List;

import com.rachid.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	private List<Cart> cartItems;
	private String userEmail;
	private String userName;
}
