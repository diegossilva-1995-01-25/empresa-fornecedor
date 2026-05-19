package br.com.diegossilva.empresa_fornecedor.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Usuario;
import br.com.diegossilva.empresa_fornecedor.demo.repository.UsuarioRepository;

@Service
public class DetalheUsuarioService implements UserDetailsService {

	private final UsuarioRepository repo;

	public DetalheUsuarioService(UsuarioRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String cpf) {
		Usuario u = repo.findById(cpf)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Cliente não encontrado: "+ cpf));
		
		return org.springframework.security.core.userdetails.User.builder()
			.username(u.getCpf())
			.password(u.getSenha())
			.authorities("ROLE_USER")
			.build();
	}
	
}