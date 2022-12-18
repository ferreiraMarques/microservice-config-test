package com.motoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motoservice.entities.Byke;

@Repository
public interface BykeRepository  extends JpaRepository<Byke, Integer> {
	
	List<Byke> findByUser(int userId);
	
}
