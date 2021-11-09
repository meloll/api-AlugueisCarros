package com.apicast.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apicast.entities.Aluguel;
import com.apicast.repositories.AluguelRepository;
import com.apicast.service.exceptions.DatabaseException;
import com.apicast.service.exceptions.ResourceNotFoundException;

@Service
public class AluguelService {
	
	@Autowired
	private AluguelRepository repository;
	
	public List<Aluguel>findAll(){
		return repository.findAll();
		
	}
	
	public Aluguel findById(Long id) {
		Optional<Aluguel> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Aluguel insert(Aluguel obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e ) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Aluguel update(Long id, Aluguel aluguel) {
		try {
			Aluguel entity = repository.getOne(id);
			updateData(entity,aluguel);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Aluguel entity, Aluguel aluguel) {
		entity.setCarro(aluguel.getCarro());
		entity.setCliente(aluguel.getCliente());
		entity.setDataEntrega(aluguel.getDataEntrega());
		entity.setDataSaida(aluguel.getDataSaida());
	}
}


