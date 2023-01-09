package com.userservice.userservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userservice.userservice.configuration.models.Byke;

@FeignClient(name = "byke-service")
@RequestMapping("/byke")
public interface BykeFeignClient {

	@PostMapping
	public Byke save(@RequestBody Byke byke);
	
	@GetMapping("/user/{id}")
	public List<Byke>  getByUserId(@PathVariable("id") int id);
	
}
