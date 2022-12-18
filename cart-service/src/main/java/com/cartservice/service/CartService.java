package com.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartservice.entities.Cart;
import com.cartservice.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	public List<Cart> getAll() {
		return this.cartRepository.findAll();
	}

	public Cart getCartById(int id) {
		return this.cartRepository.findById(id).orElse(null);
	}
	
	public Cart save(Cart cart) {
		Cart newCart = this.cartRepository.save(cart);
		return newCart;
	}
	
	public List<Cart> getCartsByUserId(int userId) {
		return this.cartRepository.findByUser(userId);
	}
	
}
