package com.algaworks.algamoneyapi.algamoney.api.resource;

import com.algaworks.algamoneyapi.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.algamoney.api.model.Categoria;
import com.algaworks.algamoneyapi.algamoney.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<?> listar(){
        List<Categoria> categorias = categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Categoria> criar(@Valid @RequestBody  Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaService.salvar(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Long codigo){
        Categoria categoria = categoriaService.consultarPorId(codigo);
        return categoria == null  ? ResponseEntity.noContent().build() : ResponseEntity.ok(categoria) ;
    }
}
