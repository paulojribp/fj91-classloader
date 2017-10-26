package br.com.caelum.fj91.classloader.infra.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AutenticadoComSucessoHandler autenticadoComSucessoHandler;

	public SpringSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder, AutenticadoComSucessoHandler autenticadoComSucessoHandler) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.autenticadoComSucessoHandler = autenticadoComSucessoHandler;
	}

	public UserDetailsService userDetailsServiceBean() {
		return userDetailsService;
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] permittedUrls = { "/", "/home", "/login" };
		String[] authenticatedUrls = { "/usuarios", "/usuarios/**/*" };
		
		http.csrf().and()
				.authorizeRequests().antMatchers(authenticatedUrls).authenticated()
				.and()
				.authorizeRequests().antMatchers(permittedUrls).permitAll()
				.and()
				.formLogin().successHandler(autenticadoComSucessoHandler).loginPage("/login");
	}
	
}
