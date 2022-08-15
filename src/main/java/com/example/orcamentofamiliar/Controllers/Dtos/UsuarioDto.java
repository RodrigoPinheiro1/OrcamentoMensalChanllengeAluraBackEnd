package com.example.orcamentofamiliar.Controllers.Dtos;

import com.example.orcamentofamiliar.Entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.nome =usuario.getNome();
        this.email = usuario.getEmail();
    }
}
