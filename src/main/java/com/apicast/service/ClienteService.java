package com.apicast.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apicast.entities.Cliente;
import com.apicast.repositories.AluguelRepository;
import com.apicast.repositories.ClienteRepository;
import com.apicast.service.exceptions.DatabaseException;
import com.apicast.service.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	//Insert
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}
	
	//Delete
	public void delete(Long id) {
		try {
			
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e ) {
			throw new DatabaseException(e.getMessage());
		}
	}

	//Update
	public Cliente update(Long id, Cliente cliente) {

		try {
			Cliente entity = repository.getOne(id);
			updateData(entity,cliente);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Cliente entity, Cliente cliente) {
		entity.setCPF(cliente.getCPF());
		entity.setDataN(cliente.getDataN());
		entity.setEmail(cliente.getEmail());
		entity.setNome(cliente.getNome());
		entity.setSenha(cliente.getSenha());
	}
}
