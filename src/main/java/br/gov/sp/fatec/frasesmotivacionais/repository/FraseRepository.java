package br.gov.sp.fatec.frasesmotivacionais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import br.gov.sp.fatec.frasesmotivacionais.entity.Frase;

public interface FraseRepository extends JpaRepository<Frase, Long>{
	
	@PreAuthorize("isAuthenticated()")
    public List<Frase> findByAutorNomeOrAutorEmail(String nome, String email);

}
