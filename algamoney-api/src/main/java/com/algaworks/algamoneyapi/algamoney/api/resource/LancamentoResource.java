package com.algaworks.algamoneyapi.algamoney.api.resource;

import com.algaworks.algamoneyapi.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.algamoney.api.exception.handler.AlgamoneyExceptionHandler;
import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.algamoney.api.service.LancamentoService;
import com.algaworks.algamoneyapi.algamoney.api.service.exception.PessoaInexistenteInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messageSource;
/*
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Lancamento> lancamentos = lancamentoService.listarTodos();

        return lancamentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lancamentos);
    }*/

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> consultarPorId(@PathVariable Long codigo) {
        Lancamento lancamento = lancamentoService.consultarPorId(codigo);

        return lancamento == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(lancamento);
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    }

    @ExceptionHandler({PessoaInexistenteInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter){
        return lancamentoService.pesquisar(lancamentoFilter);
    }
}
