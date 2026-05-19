package br.com.diegossilva.empresa_fornecedor.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
	
	List<Empresa> findAllByUsuarioCpf(Usuario u);
	
	Empresa findByCnpj(Empresa e);
	
}
