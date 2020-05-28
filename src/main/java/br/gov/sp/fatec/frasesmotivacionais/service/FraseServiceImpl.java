package br.gov.sp.fatec.frasesmotivacionais.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frasesmotivacionais.entity.Frase;
import br.gov.sp.fatec.frasesmotivacionais.entity.Usuario;
import br.gov.sp.fatec.frasesmotivacionais.repository.FraseRepository;
import br.gov.sp.fatec.frasesmotivacionais.repository.UsuarioRepository;

@Service("fraseService")
public class FraseServiceImpl implements FraseService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private FraseRepository fraseRepo;

	@Override
	@Transactional
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public Frase adicionarFrase(String titulo, String conteudo, String autor) {
		Usuario usuario = usuarioRepo.findTop1ByNomeOrEmail(
				autor, autor);
		if(usuario == null) {
			throw new UsernameNotFoundException(
				"Usuário com identificador " +
				autor +
				" não foi encontrado");
		}
		Frase frase = new Frase();
		frase.setTitulo(titulo);
		frase.setConteudo(conteudo);
		frase.setAutor(usuario);
		frase.setDataHora(new Date());
		fraseRepo.save(frase);
		
		return frase;
	}

}
