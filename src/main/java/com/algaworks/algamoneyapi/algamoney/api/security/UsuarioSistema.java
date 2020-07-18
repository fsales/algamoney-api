package com.algaworks.algamoneyapi.algamoney.api.security;

import com.algaworks.algamoneyapi.algamoney.api.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class UsuarioSistema extends User {

    private Usuario usuario;


    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getNome(), usuario.getSenha(), authorities);
    }


}
