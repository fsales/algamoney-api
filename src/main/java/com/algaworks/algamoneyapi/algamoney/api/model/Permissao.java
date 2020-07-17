package com.algaworks.algamoneyapi.algamoney.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "permissao")

@Getter
@Setter
@ToString(of = {"codigo", "descricao"})
@EqualsAndHashCode(of = {"codigo"})
public class Permissao implements Serializable {
    @Id
    private Long codigo;
    private String descricao;

}
