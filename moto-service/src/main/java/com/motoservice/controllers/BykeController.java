package com.motoservice.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoservice.entities.Byke;
import com.motoservice.service.BykeService;

@RestController
@RequestMapping("/byke")
public class BykeController {

	@Autowired
	private BykeService bykeService;

	@GetMapping
	public ResponseEntity<List<Byke>> getAll() {
		List<Byke> bykes = this.bykeService.getAll();
		return ResponseEntity.ok(bykes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Byke> getById(@PathVariable("id") int id) {
		Byke byke = this.bykeService.getCartById(id);
		if (byke == null) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.ok(byke);
	}

	@PostMapping
	public ResponseEntity<Byke> save(@RequestBody Byke byke) {
		Byke newByke = this.bykeService.save(byke);
		return ResponseEntity.ok(newByke);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<Byke>> getByUserId(@PathVariable("id") int id) {
		List<Byke> bykes = this.bykeService.getCartsByUserId(id);
		return ResponseEntity.ok(bykes);
	}

}
