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
@RequestMapping("/empresa-fornecedor/empresa-fornecedor")
@CrossOrigin(origins = "*")
public class EmpresaFornecedorController {

	@Autowired
	private EmpresaFornecedorService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<EmpresaFornecedor> cadastrarEmpresaFornecedor(@RequestBody EmpresaFornecedor empresaFornecedor) {
		ResponseEntity<EmpresaFornecedor> res;
		try {
			res = new ResponseEntity<EmpresaFornecedor>(service.cadastrarEmpresaFornecedor(empresaFornecedor), HttpStatus.OK);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<EmpresaFornecedor>(new EmpresaFornecedor(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/todos-por-empresa")
	public ResponseEntity<List<EmpresaFornecedor>> todosEmpresaFornecedoresPorEmpresa(@RequestBody Empresa empresa) {
		ResponseEntity<List<EmpresaFornecedor>> res = new ResponseEntity<List<EmpresaFornecedor>>(service.todosEmpresaFornecedoresPorEmpresa(empresa), HttpStatus.OK);
		return res;
	}
	
	@GetMapping("/todos-por-fornecedor")
	public ResponseEntity<List<EmpresaFornecedor>> registrarEmpresaFornecedor(@RequestBody Fornecedor fornecedor) {
		ResponseEntity<List<EmpresaFornecedor>> res = new ResponseEntity<List<EmpresaFornecedor>>(service.todosEmpresaFornecedoresPorFornecedor(fornecedor), HttpStatus.OK);
		return res;
	}
	
	@GetMapping("/um-registro")
	public ResponseEntity<EmpresaFornecedor> umaEmpresaFornecedor(@RequestBody Empresa empresa, @RequestBody Fornecedor fornecedor) {
		ResponseEntity<EmpresaFornecedor> res = new ResponseEntity<EmpresaFornecedor>(service.umaEmpresaFornecedor(empresa, fornecedor), HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<Void> deletarFornecedor(EmpresaFornecedor empresaFornecedor) {
		
		service.deletarEmpresaFornecedor(empresaFornecedor);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
