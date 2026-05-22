package br.com.diegossilva.empresa_fornecedor.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.repository.EmpresaRepository;

@Service
@Lazy
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;
	
	public Empresa cadastrarEmpresa(Empresa empresa) {
		return repo.save(empresa);
	}
	
	public Empresa editarEmpresa(Empresa empresa) {
		return repo.save(empresa);
	}
	
	public List<Empresa> todasEmpresas(Usuario usuario) {
		return repo.findAllByUsuarioCpf(usuario.getCpf());
	}
	
	public List<Empresa> todasEmpresasPorNome(Empresa empresa) {
		return repo.findByNomeFantasiaContaining(empresa.getNomeFantasia());
	}
	
	public Empresa umaEmpresa(Empresa empresa) {
		return repo.findByCnpj(empresa.getCnpj());
	}
	
	public void deletarEmpresa(Empresa empresa) {
		repo.delete(empresa);
	}

}
