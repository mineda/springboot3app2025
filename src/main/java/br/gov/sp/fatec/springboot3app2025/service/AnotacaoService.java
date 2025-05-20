package br.gov.sp.fatec.springboot3app2025.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springboot3app2025.entity.Anotacao;
import br.gov.sp.fatec.springboot3app2025.entity.Usuario;
import br.gov.sp.fatec.springboot3app2025.repository.AnotacaoRepository;
import br.gov.sp.fatec.springboot3app2025.repository.UsuarioRepository;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public Anotacao nova(Anotacao anotacao) {
        if(anotacao == null ||
                anotacao.getTexto() == null ||
                anotacao.getTexto().isBlank() ||
                anotacao.getUsuario() == null ||
                anotacao.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto ou usuário não informados!");
        }
        if(anotacao.getDataHora() == null) {
            anotacao.setDataHora(LocalDateTime.now());
        }
        Optional<Usuario> usuarioOp = usuarioRepo.findById(anotacao.getUsuario().getId());
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido!");
        }
        anotacao.setUsuario(usuarioOp.get());
        return repo.save(anotacao);
    }

    public List<Anotacao> todas() {
        return repo.findAll();
    }

    public List<Anotacao> buscarPorNomeUsuarioETexto(String nomeUsuario, String texto) {
        return repo.findByUsuarioNomeAndTextoContains(nomeUsuario, texto);
    }
    
}
