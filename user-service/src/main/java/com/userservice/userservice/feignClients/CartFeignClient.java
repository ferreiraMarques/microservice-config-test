package com.userservice.userservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userservice.userservice.configuration.models.Cart;

@FeignClient(name = "cart-service")
@RequestMapping("/cart")
public interface CartFeignClient {
	
	@PostMapping
	public Cart save(@RequestBody Cart cart);
	
	@GetMapping("/user/{id}")
	public List<Cart>  getByUserId(@PathVariable("id") int id);
	
}
