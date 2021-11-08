package com.apicast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicast.entities.Cliente;
import com.apicast.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	//Select
	
	public List<Cliente>findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente>obj = repository.findById(id);
		return obj.get();
	}
	
	//Insert
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}
}
