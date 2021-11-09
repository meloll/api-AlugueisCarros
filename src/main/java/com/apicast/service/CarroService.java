package com.apicast.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apicast.entities.Aluguel;
import com.apicast.entities.Carro;
import com.apicast.repositories.CarroRepository;
import com.apicast.service.exceptions.DatabaseException;
import com.apicast.service.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//INSERT
	public Carro insert(Carro obj) {
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
	
	public Carro update(Long id,Carro carro) {
		try {
			Carro entity = repository.getOne(id);
			updateData(entity,carro);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Carro entity,Carro carro ) {
		entity.setCor(carro.getCor());
		entity.setMarca(carro.getMarca());
		entity.setPlaca(carro.getPlaca());
		entity.setPreco(carro.getPreco());
	}

}
