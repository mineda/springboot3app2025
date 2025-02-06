package br.gov.sp.fatec.springboot3app2025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.sp.fatec.springboot3app2025.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public Usuario findByNome(String nome);

    @Query("select u from Usuario u where u.nome = ?1")
    public Usuario buscarPeloNome(String nome);

    public List<Usuario> findByNomeContains(String nome);

    @Query("select u from Usuario u where u.nome like %?1%")
    public List<Usuario> buscarPeloNomeContendo(String nome);

    public Usuario findByNomeAndSenha(String nome, String senha);

    @Query("select u from Usuario u where u.nome = ?1 and u.senha = ?2")
    public Usuario buscarPeloNomeESenha(String nome, String senha);

    public List<Usuario> findByAutorizacoesNome(String nomeAutorizacao);
    
    @Query("select u from Usuario u join u.autorizacoes a where a.nome = :nome")
    public List<Usuario> 
        buscarPeloNomeAutorizacao(@Param("nome") String nomeAutorizacao);

}

