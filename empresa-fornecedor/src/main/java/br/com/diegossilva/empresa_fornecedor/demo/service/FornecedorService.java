package br.com.diegossilva.empresa_fornecedor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.repository.FornecedorRepository;

@Service
@Lazy
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repo;
	
	public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {
		return repo.save(fornecedor);
	}
	
	public Fornecedor editarFornecedor(Fornecedor fornecedor) {
		return repo.save(fornecedor);
	}
	
	public List<Fornecedor> todosFornecedores(Usuario usuario) {
		return repo.findAllByUsuarioCpf(usuario);
	}
	
	public Fornecedor umFornecedor(Fornecedor fornecedor) {
		return repo.findByCpfCnpj(fornecedor);
	}
	
	public void deletarFornecedor(Fornecedor fornecedor) {
		repo.delete(fornecedor);
	}

}
