package br.com.diegossilva.empresa_fornecedor.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.service.EmpresaFornecedorService;

@RestController
@RequestMapping("/empresa-fornecedor/ef")
@CrossOrigin(origins = "*")
public class EmpresaFornecedorController {

	@Autowired
	private EmpresaFornecedorService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<EmpresaFornecedor> cadastrarEmpresaFornecedor(@RequestBody EmpresaFornecedor empresaFornecedor) {
		try {
			return new ResponseEntity<>(service.cadastrarEmpresaFornecedor(empresaFornecedor), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new EmpresaFornecedor(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/todos-por-empresa")
	public ResponseEntity<List<EmpresaFornecedor>> todosEmpresaFornecedoresPorEmpresa(@RequestBody EmpresaFornecedor empresaFornecedor) {
	    return new ResponseEntity<>(service.todosEmpresaFornecedoresPorEmpresa(empresaFornecedor.getEmpresa().getCnpj()), HttpStatus.OK);
	}

	@GetMapping("/todos-por-fornecedor")
	public ResponseEntity<List<EmpresaFornecedor>> todosEmpresaFornecedoresPorFornecedor(@RequestBody EmpresaFornecedor empresaFornecedor) {
	    return new ResponseEntity<>(service.todosEmpresaFornecedoresPorFornecedor(empresaFornecedor.getFornecedor().getCpfCnpj()), HttpStatus.OK);
	}
	
	@GetMapping("/um-registro")
	public ResponseEntity<EmpresaFornecedor> umaEmpresaFornecedor(@RequestBody EmpresaFornecedor empresaFornecedor) {
		return new ResponseEntity<>(service.umaEmpresaFornecedor(empresaFornecedor.getEmpresa().getCnpj(), empresaFornecedor.getFornecedor().getCpfCnpj()), HttpStatus.OK);
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<Void> deletarEmpresaFornecedor(@RequestBody EmpresaFornecedor empresaFornecedor) {
		service.deletarEmpresaFornecedor(empresaFornecedor);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}