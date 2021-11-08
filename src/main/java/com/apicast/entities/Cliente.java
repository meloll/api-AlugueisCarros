package com.apicast.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Pois essa classe vai representar uma tabela
@Table(name="tb_clientes")

public class Cliente implements Serializable{
	private static final long serialVersionUID=1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Date dataN;
	
	public Cliente( String nome, String cpf, String email, String senha, Date dataN) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataN = dataN;
	}
	
	//***********Métodos**************
	
	public Cliente() {
		
	}

	//Get e Set
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataN() {
		return dataN;
	}

	public void setDataN(Date dataN) {
		this.dataN = dataN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
	
	
	
	

	
	
	
	
	
	
}
