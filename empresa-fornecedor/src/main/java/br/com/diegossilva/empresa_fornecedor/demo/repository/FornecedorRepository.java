package br.com.diegossilva.empresa_fornecedor.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

	List<Fornecedor> findAllByUsuarioCpf(String cpf);
	
	List<Fornecedor> findByNomeContaining(String nome);
	
	Fornecedor findByCpfCnpj(String cpfCnpj);
	
}
