package br.com.projetafacil.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetafacil.model.Usuario;
import br.com.projetafacil.repository.Usuarios;
import br.com.projetafacil.security.UsuarioSistema;


@Service
public class UsuarioService {
	
	@Autowired
	Usuarios usuarios;

	@Transactional
	public void salvarPerfil(Usuario usuario, UsuarioSistema user) {
		
		try {
			
			Usuario persistir = usuarios.buscarComGrupos(user.getUsuario().getId());
			usuario.setGrupos(persistir.getGrupos());
			persistir.setPerfil(usuario.getPerfil());
			persistir.setNome(usuario.getNome());

			usuarios.saveAndFlush(persistir);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar o perfil de usuário." + e.getMessage());
		}
		
	}
	
	
}
