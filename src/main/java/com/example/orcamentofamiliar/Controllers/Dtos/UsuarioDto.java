package com.example.orcamentofamiliar.Controllers.Dtos;

import com.example.orcamentofamiliar.Entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;


}
