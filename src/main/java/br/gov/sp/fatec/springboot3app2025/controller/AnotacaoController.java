package br.gov.sp.fatec.springboot3app2025.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springboot3app2025.entity.Anotacao;
import br.gov.sp.fatec.springboot3app2025.service.AnotacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoService service;

    @PostMapping
    public Anotacao nova(@RequestBody Anotacao anotacao) {
        return service.nova(anotacao);
    }

    @GetMapping
    public List<Anotacao> todas() {
        return service.todas();
    }

    @GetMapping(value = "/{nome}/{texto}")
    public List<Anotacao> buscarPorNomeUsuarioETexto(@PathVariable("nome") String nomeUsuario,
            @PathVariable("texto") String texto) {
        return service.buscarPorNomeUsuarioETexto(nomeUsuario, texto);
    }

}
