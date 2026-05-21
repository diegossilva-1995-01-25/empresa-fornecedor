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
	
	public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) throws Exception {
		if(fornecedor.getCpfCnpj().length() == 11 
				&& (fornecedor.getRg() == null || fornecedor.getRg().equals("")
				|| Integer.valueOf(fornecedor.getNasc()) == null || fornecedor.getNasc() == 0 || String.valueOf(fornecedor.getNasc()).equals(""))) {
			throw new Exception("Em caso de CPF ser informado, informe também o RG e a Data de Nascimento da pessoa.");
		}
		
		return repo.save(fornecedor);
	}
	
	public Fornecedor editarFornecedor(Fornecedor fornecedor) {
		return repo.save(fornecedor);
	}
	
	public List<Fornecedor> todosFornecedores(Usuario usuario) {
		return repo.findAllByUsuarioCpf(usuario.getCpf());
	}
	
	public Fornecedor umFornecedor(Fornecedor fornecedor) {
		return repo.findByCpfCnpj(fornecedor.getCpfCnpj());
	}
	
	public void deletarFornecedor(Fornecedor fornecedor) {
		repo.delete(fornecedor);
	}

}
