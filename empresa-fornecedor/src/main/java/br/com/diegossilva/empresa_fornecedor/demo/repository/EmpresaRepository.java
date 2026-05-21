package br.com.diegossilva.empresa_fornecedor.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
	
	List<Empresa> findAllByUsuarioCpf(String cpf);
	
	Empresa findByCnpj(String cnpj);
	
}
