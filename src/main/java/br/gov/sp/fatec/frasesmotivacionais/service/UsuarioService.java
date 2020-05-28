package br.gov.sp.fatec.frasesmotivacionais.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.frasesmotivacionais.entity.Usuario;

public interface UsuarioService extends UserDetailsService {
	
	public Usuario novoUsuario(String nome, String email, 
			String senha, String autorizacao);

}
