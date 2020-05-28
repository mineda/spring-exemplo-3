package br.gov.sp.fatec.frasesmotivacionais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.frasesmotivacionais.entity.Usuario;
import br.gov.sp.fatec.frasesmotivacionais.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
    
    @PostMapping(value = "/novo")
    public Usuario cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        return usuarioService.novoUsuario(usuario.getNome(), 
                usuario.getEmail(), 
                usuario.getSenha(),
                usuario.getAutorizacao());
    }

}
