package br.com.diegossilva.empresa_fornecedor.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/empresa-fornecedor/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticar(@RequestBody Usuario usuario) {
	    
	    String[] msgEToken = service.fazerLogin(usuario);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", msgEToken[0]);
	    response.put("token", msgEToken[1]);
	    
	    return ResponseEntity.ok().body(response);
	    
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> sair(HttpServletRequest request, HttpServletResponse response) {
		
		String mensagem = service.fazerLogout(request, response);
		
		return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(mensagem);

	}
	
	@PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){

        String mensagem = service.fazerRegistro(usuario);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", mensagem);
        
        if(mensagem.contains("Erro: ")) {
        	return ResponseEntity.badRequest().body(response);
        } else {
        	return ResponseEntity.ok().body(response);
        }
        

    }
	
	@PostMapping("/editar-nome")
    public ResponseEntity<String> editarUsuarioNome(@RequestBody Usuario usuario){

        String mensagem = service.editarNome(usuario);
        
        if(mensagem.contains("Erro: ")) {
        	return ResponseEntity.badRequest().body(mensagem);
        } else {
        	return ResponseEntity.ok().body(mensagem);
        }
        

    }
	
	@PostMapping("/editar-senha")
    public ResponseEntity<String> editarUsuarioSenha(@RequestBody Usuario usuario){

        String mensagem = service.editarSenha(usuario);
        
        if(mensagem.contains("Erro: ")) {
        	return ResponseEntity.badRequest().body(mensagem);
        } else {
        	return ResponseEntity.ok().body(mensagem);
        }
        

    }

}
