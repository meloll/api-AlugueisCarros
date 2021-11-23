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

import com.apicast.entities.Aluguel;
import com.apicast.entities.Cliente;
import com.apicast.service.AluguelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/alugueis")
@CrossOrigin(origins = "*")
@Api(value="API Rest Alugueis")
public class AluguelResource {
	
	

	
	@Autowired
	private AluguelService service;
	
	@ApiOperation(value="Retorna todos os alugueis")
	@GetMapping
	public ResponseEntity<List<Aluguel>>findAll(){
		List<Aluguel>list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Retorna apenas o aluguel com o mesmo ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Aluguel>findById(@PathVariable Long id){
		Aluguel obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Insere aluguel")
	@PostMapping
	public ResponseEntity<Aluguel>insert(@RequestBody Aluguel obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}
	
	
	@ApiOperation(value="Deleta aluguel pelo ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Altera dados do aluguel pelo ID")
	@PutMapping(value="/{id}")
	public ResponseEntity <Aluguel> update(@RequestBody Aluguel aluguel,@PathVariable Long id){
		aluguel = service.update(id,aluguel);
		return ResponseEntity.ok().body(aluguel);
	}

}
