package br.gov.sp.fatec.springboot3app2025.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springboot3app2025.entity.Autorizacao;
import br.gov.sp.fatec.springboot3app2025.entity.Usuario;
import br.gov.sp.fatec.springboot3app2025.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springboot3app2025.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

    @Transactional
    public Usuario novoUsuarioAutorizacao(String nome, 
            String senha, String nomeAutorizacao) {
        Usuario usuario = new Usuario(nome, senha);
        Optional<Autorizacao> autOp = autRepo.findByNome(nomeAutorizacao);
        Autorizacao autorizacao;
        if(autOp.isEmpty()) {
            autorizacao = new Autorizacao();
            autorizacao.setNome(nomeAutorizacao);
            autRepo.save(autorizacao);
        }
        else {
            autorizacao = autOp.get();
        }
        usuario.setAutorizacoes(new ArrayList<Autorizacao>());
        usuario.getAutorizacoes().add(autorizacao);
        return usuarioRepo.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isPresent()) {
            return usuarioOp.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id inválido!");
    }

    public Usuario novoUsuario(Usuario usuario) {
        if(usuario == null ||
                usuario.getNome() == null ||
                usuario.getSenha() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e senha inválidos!");
        }
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

}
