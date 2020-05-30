package com.algaworks.algamoneyapi.algamoney.api.repository.lancamento;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
