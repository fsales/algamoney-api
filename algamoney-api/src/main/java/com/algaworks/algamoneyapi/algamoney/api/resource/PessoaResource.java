package com.algaworks.algamoneyapi.algamoney.api.resource;

import com.algaworks.algamoneyapi.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.algamoney.api.model.Pessoa;
import com.algaworks.algamoneyapi.algamoney.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;


    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        return pessoas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> consultarPorId(@PathVariable Long codigo){
        Pessoa pessoa = pessoaService.consultarPorId(codigo);

        return pessoa == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        pessoaService.deletar(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {

        return ResponseEntity.ok(pessoaService.atualizar(codigo, pessoa));
    }
}
