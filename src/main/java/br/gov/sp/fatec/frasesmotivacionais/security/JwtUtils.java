package br.gov.sp.fatec.frasesmotivacionais.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.sp.fatec.frasesmotivacionais.controller.UsuarioDTO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUtils {
    
    private static final String KEY = "spring.jwt.sec";
    
    public static String generateToken(User usuario) 
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UsuarioDTO usuarioSemSenha = new UsuarioDTO();
        usuarioSemSenha.setNome(usuario.getUsername());
        if(!usuario.getAuthorities().isEmpty()) {
            usuarioSemSenha.setAutorizacao(
                    usuario.getAuthorities().iterator()
                    .next().getAuthority());
        }
        String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L; // Uma hora
        return Jwts.builder().claim("userDetails", usuarioJson)
            .setIssuer("br.gov.sp.fatec")
            .setSubject(usuario.getUsername())
            .setExpiration(new Date(agora.getTime() + hora))
            .signWith(SignatureAlgorithm.HS512, KEY)
            .compact();
    }
    
    public static Authentication parseToken(String token) 
               throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson =
            Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("userDetails", String.class);
        Login usuario = mapper.readValue(credentialsJson, Login.class);
        UserDetails userDetails = 
            User.builder()
                .username(usuario.getUsername())
                .password("secret")
                .authorities(usuario.getAutorizacao())
                .build();
        return new UsernamePasswordAuthenticationToken(usuario.getUsername(),
            usuario.getPassword(),
            userDetails.getAuthorities());
    }

}
