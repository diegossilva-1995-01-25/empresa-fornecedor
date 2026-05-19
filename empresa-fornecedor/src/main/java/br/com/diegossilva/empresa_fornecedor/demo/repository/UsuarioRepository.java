package br.com.diegossilva.empresa_fornecedor.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	boolean existsById(String id);

}
