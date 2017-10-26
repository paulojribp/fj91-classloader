package br.com.caelum.fj91.classloader.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String PAGINA_LOGIN = "login/login";
	
	@GetMapping
	public String login() {
		return PAGINA_LOGIN;
	}
	
}
