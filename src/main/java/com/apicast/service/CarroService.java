package com.apicast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicast.entities.Carro;
import com.apicast.repositories.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	//SELECT
	public List<Carro>findAll(){
		return repository.findAll();
	}
	
	public Carro findById(Long id) {
		Optional<Carro> obj = repository.findById(id);
		return obj.get();
	}
	
	//INSERT
	public Carro insert(Carro obj) {
		return repository.save(obj);
	}
	

}
