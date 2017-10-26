package br.com.caelum.fj91.classloader.infra.spring;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.caelum.fj91.classloader.models.Usuario;

@Component
public class LogoutOrSessionExpiredListener implements HttpSessionListener, ApplicationContextAware {
	
	@Autowired
	private final UsuariosLogados usuariosLogados;
	
	public LogoutOrSessionExpiredListener(UsuariosLogados usuariosLogados) {
		this.usuariosLogados = usuariosLogados;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext instanceof WebApplicationContext) {
			((WebApplicationContext) applicationContext).getServletContext().addListener(this);
		} else {
			throw new RuntimeException("Must be inside a web application context");
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		SecurityContext ctx = (SecurityContext) event.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (ctx != null) {
			Usuario logado = (Usuario) ctx.getAuthentication().getPrincipal();
			usuariosLogados.removerUsuarioLogado(logado);
		}
	}

}
