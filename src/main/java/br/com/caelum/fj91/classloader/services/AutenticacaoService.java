package br.com.caelum.fj91.classloader.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caelum.fj91.classloader.models.Usuario;
import br.com.caelum.fj91.classloader.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	private final UsuarioRepository repository;

	public AutenticacaoService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repository.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não cadastrado!");
		}

		return usuario;
	}

}
