package com.userservice.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.userservice.configuration.models.Byke;
import com.userservice.userservice.configuration.models.Cart;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.feignClients.BykeFeignClient;
import com.userservice.userservice.feignClients.CartFeignClient;
import com.userservice.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartFeignClient cartFeignClient;
	
	@Autowired
	private BykeFeignClient bykeFeignClient;

	@Autowired
	private RestTemplate restTemplate;

	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	public User getUserById(int id) {
		return this.userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		User newUser = this.userRepository.save(user);
		return newUser;
	}

//	public List<Cart> getCarts(int userId) {
//		List<Cart> carts = this.restTemplate.getForObject("http://localhost:8002/cart/user/" + userId, List.class);
//		return carts;
//	}
//
//	public List<Byke> getBykes(int userId) {
//		List<Byke> bykes = this.restTemplate.getForObject("http://localhost:8003/byke/user/" + userId, List.class);
//		return bykes;
//	}
//	
	public Cart saveCart(int userId, Cart cart) {
		cart.setUserId(userId);
		Cart newCart = this.cartFeignClient.save(cart);
		return newCart;
	}
	
	public List<Cart> getCarts(int userId) {
		List<Cart> carts = this.cartFeignClient.getByUserId(userId);
		return carts;
	}

	public List<Byke> getBykes(int userId) {
		List<Byke> bykes = this.bykeFeignClient.getByUserId(userId);
		return bykes;
	} 
	
	public Byke saveByke(int userId, Byke byke) {
		byke.setUserId(userId);
		Byke newByke = this.bykeFeignClient.save(byke);
		return newByke;
	}
	
	public Map<String, Object> getUserAndVehicles(int userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			result.put("message", "user not found");
			return result;
		}
		
		List<Cart> carts = this.getCarts(userId);
		List<Byke> bykes = this.getBykes(userId);
		
		result.put("user", user);
		result.put("carts", carts);
		result.put("bykes", bykes);
		
		return result;
	}
}
