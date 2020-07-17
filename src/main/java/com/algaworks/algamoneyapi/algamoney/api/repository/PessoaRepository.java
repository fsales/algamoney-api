package com.algaworks.algamoneyapi.algamoney.api.repository;

import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
