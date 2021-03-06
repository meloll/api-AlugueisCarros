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

import com.apicast.entities.Cliente;
import com.apicast.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/clientes")
@CrossOrigin(origins = "*")
@Api(value="API REST Cliente")
public class ClienteResource {
	
	//parte da api
	
	@Autowired
	private ClienteService service;
	
	@ApiOperation(value="Retorna todos os clientes")
	@GetMapping
	public ResponseEntity<List<Cliente>>findAll(){
		List<Cliente>list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Retorna apenas o cliente com o ID igual")
	@GetMapping(value="/{id}")
	public ResponseEntity<Cliente>findById(@PathVariable Long id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Insere um cliente")
	@PostMapping
	public ResponseEntity<Cliente>insert(@RequestBody Cliente obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}
	
	@ApiOperation(value="Deleta um cliente pelo ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Atualiza os dados do cliente pelo ID")
	@PutMapping(value="/{id}")
	public ResponseEntity <Cliente> update(@RequestBody Cliente cliente,@PathVariable Long id){
		cliente = service.update(id,cliente);
		return ResponseEntity.ok().body(cliente);
	}

}
