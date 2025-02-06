package br.gov.sp.fatec.springboot3app2025.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springboot3app2025.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{

    public Optional<Autorizacao> findByNome(String nome);
    
}
