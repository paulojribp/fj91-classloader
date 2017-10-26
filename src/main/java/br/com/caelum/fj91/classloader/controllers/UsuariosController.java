package br.com.caelum.fj91.classloader.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.fj91.classloader.infra.spring.UsuariosLogados;
import br.com.caelum.fj91.classloader.models.Usuario;
import br.com.caelum.fj91.classloader.repositories.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private static final String PAGINA_USUARIOS = "usuarios/usuarios";
	private static final String REDIRECT_PAGINA_USUARIOS = "redirect:/usuarios";
	
	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder bCrypt;
	private final UsuariosLogados usuariosLogados;

	public UsuariosController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCrypt, UsuariosLogados usuariosLogados) {
		this.usuarioRepository = usuarioRepository;
		this.bCrypt = bCrypt;
		this.usuariosLogados = usuariosLogados;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("usuariosLogados", usuariosLogados);
		return PAGINA_USUARIOS;
	}
	
	@PostMapping
	@Transactional
	public String adicionar(Usuario usuario) {
		String hashSenha = bCrypt.encode(usuario.getSenha());
		usuario.setSenha(hashSenha);
		
		this.usuarioRepository.save(usuario);
		return REDIRECT_PAGINA_USUARIOS;
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public String remover(@PathVariable("id") Long id) {
		this.usuarioRepository.delete(id);
		return REDIRECT_PAGINA_USUARIOS;
	}
	
}
