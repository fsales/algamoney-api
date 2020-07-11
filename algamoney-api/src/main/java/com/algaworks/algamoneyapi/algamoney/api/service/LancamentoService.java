package com.algaworks.algamoneyapi.algamoney.api.service;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.algamoney.api.repository.lancamento.LancamentoRepository;
import com.algaworks.algamoneyapi.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoneyapi.algamoney.api.service.exception.PessoaInexistenteInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Lancamento> listarTodos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento consultarPorId(Long codigo) {
        Lancamento lancamento = lancamentoRepository.getOne(codigo);

        if (lancamento == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return lancamento;
    }

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());

        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteInativaException();
        }


        return lancamentoRepository.save(lancamento);
    }

    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    public void delete(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }
}
