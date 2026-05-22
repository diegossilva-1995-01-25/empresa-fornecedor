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

import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.service.FornecedorService;

@RestController
@RequestMapping("/empresa-fornecedor/fornecedor")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class FornecedorController {

	@Autowired
	private FornecedorService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
		
		ResponseEntity<Fornecedor> res;
		
		try {
			res = new ResponseEntity<Fornecedor>(service.cadastrarFornecedor(fornecedor), HttpStatus.OK);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Fornecedor>(new Fornecedor(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/editar")
	public ResponseEntity<Fornecedor> editarFornecedor(@RequestBody Fornecedor fornecedor) {
		
		ResponseEntity<Fornecedor> res = new ResponseEntity<Fornecedor>(service.editarFornecedor(fornecedor), HttpStatus.OK);
		return res;
		
	}
	
	@PostMapping("/todos")
	public ResponseEntity<List<Fornecedor>> todasEmpresas(@RequestBody Usuario usuario) {
		ResponseEntity<List<Fornecedor>> res = new ResponseEntity<List<Fornecedor>>(service.todosFornecedores(usuario), HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/um-registro")
	public ResponseEntity<Fornecedor> umaEmpresa(@RequestBody Fornecedor fornecedor) {
		ResponseEntity<Fornecedor> res = new ResponseEntity<Fornecedor>(service.umFornecedor(fornecedor), HttpStatus.OK);
		return res;
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<Void> deletarFornecedor(@RequestBody Fornecedor fornecedor) {
		
		service.deletarFornecedor(fornecedor);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
}
