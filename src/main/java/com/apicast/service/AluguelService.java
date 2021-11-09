package com.apicast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicast.entities.Aluguel;
import com.apicast.repositories.AluguelRepository;

@Service
public class AluguelService {
	
	@Autowired
	private AluguelRepository repository;
	
	public List<Aluguel>findAll(){
		return repository.findAll();
	}
	
	public Aluguel findById(Long id) {
		Optional<Aluguel> obj = repository.findById(id);
		return obj.get();
	}
	
	public Aluguel insert(Aluguel obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Aluguel update(Long id, Aluguel aluguel) {
		Aluguel entity = repository.getOne(id);
		updateData(entity,aluguel);
		return repository.save(entity);
	}
	
	private void updateData(Aluguel entity, Aluguel aluguel) {
		entity.setCarro(aluguel.getCarro());
		entity.setCliente(aluguel.getCliente());
		entity.setDataEntrega(aluguel.getDataEntrega());
		entity.setDataSaida(aluguel.getDataSaida());
	}
}


