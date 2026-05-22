package br.com.diegossilva.empresa_fornecedor.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.service.EmpresaService;

@RestController
@RequestMapping("/empresa-fornecedor/empresa")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody Empresa empresa) {
		
		ResponseEntity<Empresa> res = new ResponseEntity<Empresa>(service.cadastrarEmpresa(empresa), HttpStatus.OK);
		return res;
		
	}
	
	@PostMapping("/editar")
	public ResponseEntity<Empresa> editarEmpresa(@RequestBody Empresa empresa) {
		
		ResponseEntity<Empresa> res = new ResponseEntity<Empresa>(service.editarEmpresa(empresa), HttpStatus.OK);
		return res;
		
	}
	
	@PostMapping("/todos")
	public ResponseEntity<List<Empresa>> todasEmpresas(@RequestBody Usuario usuario) {
		ResponseEntity<List<Empresa>> res = new ResponseEntity<List<Empresa>>(service.todasEmpresas(usuario), HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/um-registro")
	public ResponseEntity<Empresa> umaEmpresa(@RequestBody Empresa empresa) {
		ResponseEntity<Empresa> res = new ResponseEntity<Empresa>(service.umaEmpresa(empresa), HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<Void> deletarEmpresa(@RequestBody Empresa empresa) {
		
		service.deletarEmpresa(empresa);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

}
