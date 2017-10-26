package br.com.caelum.fj91.classloader.infra.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.schooner.MemCached.BinaryClient;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import br.com.caelum.fj91.classloader.models.Usuario;

@Component
public class UsuariosLogados {
	
	private MemCachedClient memCached;
	
	@Value("${memcached.server}")
	private String server;
	
	@PostConstruct
	private void configurarMemCached() throws Exception {
		String[] servers = { server };
		SockIOPool pool = SockIOPool.getInstance("MemcachedPool");
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setNagle(false);
		pool.setAliveCheck(true);
		pool.initialize();
		this.memCached = new BinaryClient("MemcachedPool");
	}

	public void adicionarUsuarioLogado(Usuario logado) {
		if (logado == null) {
			throw new IllegalArgumentException("Usuario logado nao pode ser null");
		}

		try {
			memCached.set(logado.getId().toString(), logado);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao adicionar Usuario Logado no servidor MemCached: " +e);
		}
	}
	
	public void removerUsuarioLogado(Usuario logado) {
		if (logado == null) {
			throw new IllegalArgumentException("Usuario logado nao pode ser null");
		}
		
		try {
			String idUsuario = logado.getId().toString();
			memCached.delete(idUsuario);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao remover Usuario Logado no servidor MemCached: " +e);
		}
	}

	public boolean usuarioEstaLogado(Long idUsuario) {
		if (idUsuario == null) {
			throw new IllegalArgumentException("Id do usuario nao pode ser null");
		}
		
		try {
			Usuario usuario = (Usuario) memCached.get(idUsuario.toString());
			return usuario != null;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao carregar dados do usuario no Memcached: " +e);
		}
	}
	
}
