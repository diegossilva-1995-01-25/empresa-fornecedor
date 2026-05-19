package br.com.diegossilva.empresa_fornecedor.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;

}
