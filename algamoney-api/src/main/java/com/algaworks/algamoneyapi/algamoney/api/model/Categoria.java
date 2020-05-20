package com.algaworks.algamoneyapi.algamoney.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@ToString(of = {"id", "nome"})
@EqualsAndHashCode(of = {"id"})
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
}
