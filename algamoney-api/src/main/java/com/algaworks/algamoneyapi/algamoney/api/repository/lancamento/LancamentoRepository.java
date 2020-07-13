package com.algaworks.algamoneyapi.algamoney.api.repository.lancamento;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
