package br.com.diegossilva.empresa_fornecedor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.repository.EmpresaFornecedorRepository;

@Service
@Lazy
public class EmpresaFornecedorService {

	@Autowired
	private EmpresaFornecedorRepository repo;
	
	public EmpresaFornecedor registrarEmpresaFornecedor(EmpresaFornecedor empresaFornecedor) {
		return repo.save(empresaFornecedor);
	}
	public List<EmpresaFornecedor> todosEmpresaFornecedoresPorEmpresa(Empresa empresa) {
		return repo.findAllByEmpresaCnpj(empresa);
	}
	
	public List<EmpresaFornecedor> todosEmpresaFornecedoresPorFornecedor(Fornecedor fornecedor) {
		return repo.findAllByFornecedorCpfCnpj(fornecedor);
	}
	
	public EmpresaFornecedor umaEmpresaFornecedor(Empresa empresa, Fornecedor fornecedor) {
		return repo.findByEmpresaCnpjAndFornecedorCpfCnpj(empresa, fornecedor);
	}
	
	public void deletarEmpresaFornecedor(EmpresaFornecedor empresaFornecedor) {
		repo.delete(empresaFornecedor);
	}
	
}
