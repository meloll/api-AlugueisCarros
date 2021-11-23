package com.apicast.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_aluguel")
public class Aluguel implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date dataSaida;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date dataEntrega;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="carro_id")
	private Carro carro;
	
	
	public double getValor() {
		Long diaSaida= this.dataSaida.getTime();
		Long diaEntrega= this.dataEntrega.getTime();
		Long milleSeconds= diaEntrega-diaSaida;
		int dia = 1+((int) (milleSeconds * 1.1574074074067E-8));
		
		double valor = dia * carro.getPreco();
		
		
		
		return valor;
	}
	public int getDia() {
		Long diaSaida= this.dataSaida.getTime();
		Long diaEntrega= this.dataEntrega.getTime();
		Long milleSeconds= diaEntrega-diaSaida;
		int diaDeUso = 1+((int) (milleSeconds * 1.1574074074067E-8));
		return diaDeUso;
	}
	
	
	
	
	
	public Aluguel() {
		
	}
	

	public Aluguel(Date dataSaida, Date dataEntrega, Cliente cliente, Carro carro) {
		super();
		this.dataSaida = dataSaida;
		this.dataEntrega = dataEntrega;
		this.cliente = cliente;
		this.carro = carro;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}
	


	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluguel other = (Aluguel) obj;
		return Objects.equals(id, other.id);
	} 
	
	
	
	
	
	
	
	
}
