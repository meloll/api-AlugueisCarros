package com.apicast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.apicast.entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}
