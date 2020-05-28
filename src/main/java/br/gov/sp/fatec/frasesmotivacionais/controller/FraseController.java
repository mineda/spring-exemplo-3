package br.gov.sp.fatec.frasesmotivacionais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.frasesmotivacionais.entity.Frase;
import br.gov.sp.fatec.frasesmotivacionais.repository.FraseRepository;
import br.gov.sp.fatec.frasesmotivacionais.service.FraseService;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/frase")
@CrossOrigin
public class FraseController {

    @Autowired
    private FraseService fraseService;
    
    @Autowired
    private FraseRepository fraseRepo;
    
    @PostMapping(value = "/nova")
    @JsonView(View.FraseCompleta.class)
    public Frase cadastrarFrase(@RequestBody FraseDTO frase) {
        return fraseService.adicionarFrase(frase.getTitulo(), 
                frase.getConteudo(),
                frase.getUsuario());
    }
    
    @GetMapping(value = "/busca/{autor}")
    @JsonView(View.FraseCompleta.class)
    public List<Frase> buscarPorTitulo(
    		@PathVariable("autor") String autor) {
    	return fraseRepo.findByAutorNomeOrAutorEmail(autor, autor);
    }

    
}

