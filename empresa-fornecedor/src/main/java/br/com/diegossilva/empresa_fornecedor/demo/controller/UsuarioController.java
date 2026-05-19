package br.com.diegossilva.empresa_fornecedor.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/empresa-fornecedor/api/auth")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticar(@RequestBody Usuario usuario) {
		
		String[] msgEToken = service.fazerLogin(usuario);
		
		MultiValueMap<String, String> cabecalho = new LinkedMultiValueMap<>();
		cabecalho.add("Authorization", msgEToken[1]);
		HttpHeaders httpHeaders = HttpHeaders.copyOf(cabecalho);
		
		return ResponseEntity.ok().headers(httpHeaders).body(msgEToken[0]);

	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> sair(HttpServletRequest request, HttpServletResponse response) {
		
		String mensagem = service.fazerLogout(request, response);
		
		
		return ResponseEntity.ok().headers(HttpHeaders.EMPTY).body(mensagem);

	}
	
	@PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario){

        String mensagem = service.fazerRegistro(usuario);
        
        if(mensagem.contains("Erro: ")) {
        	return ResponseEntity.badRequest().body(mensagem);
        } else {
        	return ResponseEntity.ok().body(mensagem);
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
