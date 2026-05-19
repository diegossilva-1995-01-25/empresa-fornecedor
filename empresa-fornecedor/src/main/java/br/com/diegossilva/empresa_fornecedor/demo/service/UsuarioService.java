package br.com.diegossilva.empresa_fornecedor.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.repository.UsuarioRepository;
import br.com.diegossilva.empresa_fornecedor.demo.security.JWTHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Lazy
public class UsuarioService {
	
	@Autowired
    private AuthenticationManager gerenciadorAutenticacao;
	
	private UsuarioRepository repo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SecurityContextLogoutHandler logoutHandler;
	
	public UsuarioService(UsuarioRepository repo) {
		this.repo = repo;
	}
	
	public String[] fazerLogin(Usuario usuario) {
		
		String[] respostas = new String[2];
		
		Authentication autenticacao = gerenciadorAutenticacao.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getCpf(), usuario.getSenha()));
		
		String token = JWTHelper.generateToken(usuario.getCpf());
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		usuario = repo.findById(usuario.getCpf()).get();
		
		respostas[0] = "Seja bem-vindo, " + usuario.getNome();
		respostas[1] = token;
		
		return respostas;
		
	}
	
	public String fazerRegistro(Usuario usuario) {
		
		if(repo.existsById(usuario.getCpf())){
            return "Erro: E-mail já existe na base de dados.";
        }
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        repo.save(usuario);
		
		return "Você foi registrado com sucesso no sistema, " + usuario.getNome();
		
	}
	
	public String editarNome(Usuario usuario) {

        repo.save(usuario);
		
		return "Você teve o nome alterado com sucesso no sistema, " + usuario.getNome();
		
	}
	
	public String editarSenha(Usuario usuario) {
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        repo.save(usuario);
		
		return "Você alterou a senha com sucesso no sistema, " + usuario.getNome();
		
	}
	
	public String fazerLogout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	        logoutHandler.logout(request, response, authentication);
	    }
	    SecurityContextHolder.clearContext();
	    return "Sessão encerrada.";
	}

}
