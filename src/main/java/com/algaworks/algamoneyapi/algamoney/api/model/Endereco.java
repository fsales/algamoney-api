package com.algaworks.algamoneyapi.algamoney.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Endereco implements Serializable {
    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;
}
