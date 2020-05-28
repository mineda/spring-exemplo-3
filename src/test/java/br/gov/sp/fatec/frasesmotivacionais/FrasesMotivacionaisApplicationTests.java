package br.gov.sp.fatec.frasesmotivacionais;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.frasesmotivacionais.entity.Frase;
import br.gov.sp.fatec.frasesmotivacionais.entity.Usuario;
import br.gov.sp.fatec.frasesmotivacionais.repository.FraseRepository;
import br.gov.sp.fatec.frasesmotivacionais.repository.UsuarioRepository;
import br.gov.sp.fatec.frasesmotivacionais.service.FraseService;
import br.gov.sp.fatec.frasesmotivacionais.service.UsuarioService;

@SpringBootTest
@Transactional
@Rollback
class FrasesMotivacionaisApplicationTests {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private FraseRepository fraseRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FraseService fraseService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void usuarioRepositorySaveTest() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("Senha");
		
		usuarioRepo.save(usuario);
		
		assertNotNull(usuario.getId());
	}
	
	@Test
	void fraseRepositorySaveTest() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@email.com");
		usuario.setSenha("Senha");
		
		usuarioRepo.save(usuario);
		
		Frase frase = new Frase();
		frase.setTitulo("Teste");
		frase.setConteudo("Testar é um teste");
		frase.setDataHora(new Date());
		frase.setAutor(usuario);
		
		fraseRepo.save(frase);
		
		assertNotNull(frase.getId());
	}
	
	@Test
	void usuarioServiceNovoUsuarioTest() {
		Usuario usuario = usuarioService.novoUsuario(
				"Teste", 
				"teste@email.com", 
				"Senha", 
				"ROLE_ADMIN");		
		assertNotNull(usuario.getId());
	}
	
	@Test
	void fraseServiceAdicionarFraseTest() {
		usuarioService.novoUsuario(
				"Teste", 
				"teste@email.com", 
				"Senha", 
				"ROLE_ADMIN");
		Frase frase = fraseService.adicionarFrase( 
				"Teste", 
				"Testar é um teste",
				"teste@email.com");
		assertNotNull(frase.getId());
	}

}
