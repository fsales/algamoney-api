package com.algaworks.algamoneyapi.algamoney.api.resource;

import com.algaworks.algamoneyapi.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Lancamento> lancamentos = lancamentoService.listarTodos();

        return lancamentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lancamentos);
    }

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
}
