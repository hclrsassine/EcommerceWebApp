package com.rachid.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rachid.model.Order;
import com.rachid.repository.OrderRepository;

@Service
public class OrderService {
	private OrderRepository orderRepo;
	
	public Order getOrderDetails(Long orderId) {
		Optional<Order> order = this.orderRepo.findById(orderId);
		return order.isPresent() ? order.get() : null;
	}
	
	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}
}
