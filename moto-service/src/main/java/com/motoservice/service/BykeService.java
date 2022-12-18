package com.motoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motoservice.entities.Byke;
import com.motoservice.repository.BykeRepository;

@Service
public class BykeService {

	@Autowired
	private BykeRepository bykeRepository;

	public List<Byke> getAll() {
		return this.bykeRepository.findAll();
	}

	public Byke getCartById(int id) {
		return this.bykeRepository.findById(id).orElse(null);
	}

	public Byke save(Byke byke) {
		Byke newByke = this.bykeRepository.save(byke);
		return newByke;
	}

	public List<Byke> getCartsByUserId(int userId) {
		return this.bykeRepository.findByUser(userId);
	}

}
