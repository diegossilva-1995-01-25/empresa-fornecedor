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
public class Empresa {
	
	@Id
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String nomeFantasia;
	
	@Column(nullable = false)
	private int cep;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cpf_usuario", nullable = false)
	private Usuario usuario;

}
