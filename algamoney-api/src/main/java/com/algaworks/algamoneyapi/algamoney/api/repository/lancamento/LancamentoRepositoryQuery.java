package com.algaworks.algamoneyapi.algamoney.api.repository.lancamento;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
