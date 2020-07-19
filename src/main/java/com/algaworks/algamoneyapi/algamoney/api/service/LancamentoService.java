package com.algaworks.algamoneyapi.algamoney.api.service;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import com.algaworks.algamoneyapi.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.algamoney.api.repository.lancamento.LancamentoRepository;
import com.algaworks.algamoneyapi.algamoney.api.repository.projection.LancamentoResumo;
import com.algaworks.algamoneyapi.algamoney.api.service.exception.PessoaInexistenteInativaException;
import org.springframework.beans.BeanUtils;
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
       validaPessoa(lancamento);

        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoExistente = lancamentoRepository.getOne(codigo);

        if(lancamento.getPessoa().equals(lancamentoExistente.getPessoa())){
            validaPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento, lancamentoExistente, "codigo");

        return lancamentoRepository.save(lancamentoExistente);
    }

    private void validaPessoa(Lancamento lancamento) {

        Pessoa pesssoa = null;
        if (lancamento.getPessoa().getCodigo() != null){
            pesssoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
        }

        if(pesssoa == null || pesssoa.isInativo()){
            throw  new PessoaInexistenteInativaException();
        }
    }

    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    public void delete(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

    public Page<LancamentoResumo> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.resumir(lancamentoFilter, pageable);
    }
}
