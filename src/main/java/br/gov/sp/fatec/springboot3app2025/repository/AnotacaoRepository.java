package br.gov.sp.fatec.springboot3app2025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3app2025.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>{

    public List<Anotacao> findByUsuarioNomeAndTextoContains(String nomeUsuario, String texto);
    
}
