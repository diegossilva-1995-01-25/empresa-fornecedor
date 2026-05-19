package br.com.diegossilva.empresa_fornecedor.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Fornecedor {
	
	@Id
	@Column(nullable = false)
	private String cpfCnpj;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private int cep;
	
	@Column(nullable = false)
	private String rg;
	
	@Column(nullable = false)
	private int nasc;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cpf_usuario", nullable = false)
	private Usuario usuario;

}
