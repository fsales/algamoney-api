package com.algaworks.algamoneyapi.algamoney.api.service;

import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import com.algaworks.algamoneyapi.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = consultarPorId(codigo);

        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public Pessoa consultarPorId(Long codigo) {
        Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public void deletar(Long codigo) {
        pessoaRepository.delete(codigo);
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
