package br.com.caelum.fj91.classloader.infra.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.caelum.fj91.classloader.models.Usuario;

@Component
public class AutenticadoComSucessoHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private final UsuariosLogados logados;

	public AutenticadoComSucessoHandler(UsuariosLogados logados) {
		this.logados = logados;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
		Usuario logado = (Usuario) auth.getPrincipal();
		logados.adicionarUsuarioLogado(logado);
		super.onAuthenticationSuccess(req, res, auth);
	}
	
}
