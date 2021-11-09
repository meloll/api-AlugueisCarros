package com.apicast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	//Delete
	public void delete(Long id) {
		repository.deleteById(id);
	}

	//Update
	public Cliente update(Long id, Cliente cliente) {
		Cliente entity = repository.getOne(id);
		updateData(entity,cliente);
		return repository.save(entity);
	}
	
	private void updateData(Cliente entity, Cliente cliente) {
		entity.setCPF(cliente.getCPF());
		entity.setDataN(cliente.getDataN());
		entity.setEmail(cliente.getEmail());
		entity.setNome(cliente.getNome());
		entity.setSenha(cliente.getSenha());
	}
}
