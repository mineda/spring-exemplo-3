package br.gov.sp.fatec.frasesmotivacionais.service;

import br.gov.sp.fatec.frasesmotivacionais.entity.Frase;

public interface FraseService {
	
	public Frase adicionarFrase(String titulo, String conteudo, 
			String autor);

}
