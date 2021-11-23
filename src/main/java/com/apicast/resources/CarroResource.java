package com.apicast.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apicast.entities.Carro;
import com.apicast.service.CarroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/carros")
@CrossOrigin(origins = "*")
@Api(value="API Rest Carros")
public class CarroResource {
	
//parte da API
	
	@Autowired
	private CarroService service;
	
	
	@ApiOperation(value="Retorna todos os carros")
	@GetMapping
	public ResponseEntity<List<Carro>> findAll(){
		List<Carro>list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Retorna um carro pelo ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Carro>findById(@PathVariable Long id){
		Carro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Insere um carro")
	@PostMapping
	public ResponseEntity<Carro>insert(@RequestBody Carro obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@ApiOperation(value="Deleta carro pelo ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@ApiOperation(value="Altera dados do carro pelo ID")
	@PutMapping(value="/{id}")
	public ResponseEntity<Carro> update(@RequestBody Carro carro,@PathVariable Long id){
		carro = service.update(id,carro);
		return ResponseEntity.ok().body(carro);
	}
	
	
}
