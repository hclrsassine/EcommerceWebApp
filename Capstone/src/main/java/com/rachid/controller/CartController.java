package com.rachid.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachid.CheckoutDetails;
import com.rachid.OrderDetails;
import com.rachid.model.Order;
import com.rachid.model.User;
import com.rachid.services.GroupUserDetailsService;
import com.rachid.services.OrderService;
import com.rachid.services.ProductServices;

@RestController
@RequestMapping("/order")
public class CartController {
	private OrderService orderService;
	private ProductServices productService;
	private GroupUserDetailsService userService;
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
	
	@GetMapping("/getOrder/{orderId}")
	public Order getOrderDetails(@PathVariable Long orderId){
		Order order = orderService.getOrderDetails(orderId);
		return order;
	}
	
	@PostMapping("/placeOrder")
	public CheckoutDetails placeOrder(@RequestBody OrderDetails orderDetails) {
		CheckoutDetails checkoutDetails = new CheckoutDetails();
		User user = new User(null, orderDetails.getUserName(), null, null, orderDetails.getUserEmail(), null, null);
		
		Order order = new Order(0, user,orderDetails.getCartItems());
		order = orderService.saveOrder(order);
		
		checkoutDetails.setDate(now.format(format));
		checkoutDetails.setInvoiceNumber(new Random().nextInt(1000));
		checkoutDetails.setOrderId(order.getOrderId());
		//maybe order desc,, check products if they have desc 
		
		return checkoutDetails;
	}
}
