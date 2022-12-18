package com.userservice.userservice.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.userservice.configuration.models.Byke;
import com.userservice.userservice.configuration.models.Cart;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> users = this.userService.getAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id) {
		User user = this.userService.getUserById(id);
		if (user == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User newUser = this.userService.save(user);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/carts/{id}")
	public ResponseEntity<List<Cart>> getCarts(@PathVariable("id") int id) {
		User user = this.userService.getUserById(id);
		if (user == null) {
			ResponseEntity.notFound().build();
		}

		List<Cart> carts = this.userService.getCarts(user.getId());
		return ResponseEntity.ok(carts);
	}

	@GetMapping("/bykes/{id}")
	public ResponseEntity<List<Byke>> getBykes(@PathVariable("id") int id) {
		User user = this.userService.getUserById(id);
		if (user == null) {
			ResponseEntity.notFound().build();
		}

		List<Byke> bykes = this.userService.getBykes(user.getId());
		return ResponseEntity.ok(bykes);
	}
	
	@PostMapping("/user/{id}/cart")
	public ResponseEntity<Cart> saveCart(@PathVariable("id") int id, @RequestBody Cart cart) {
		Cart newCart = this.userService.saveCart(id, cart);
		return ResponseEntity.ok(newCart);
	}
	
	@PostMapping("/user/{id}/byke")
	public ResponseEntity<Byke> saveCart(@PathVariable("id") int id, @RequestBody Byke byke) {
		Byke newByke = this.userService.saveByke(id, byke);
		return ResponseEntity.ok(newByke);
	}
	
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Map<String, Object>> getUserVehicles(@PathVariable("id") int id) {
		Map<String, Object> userVehicles = this.userService.getUserAndVehicles(id);
		return ResponseEntity.ok(userVehicles);
	}
}
