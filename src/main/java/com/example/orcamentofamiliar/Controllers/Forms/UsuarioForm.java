package com.example.orcamentofamiliar.Controllers.Forms;

import com.example.orcamentofamiliar.Entidades.Usuario;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

@Getter
public class UsuarioForm {

    @NotNull
    private String nome;

    private String senha;
    @NotNull
    private String email;
    public Usuario cadastrar() {
        BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(senha);
        return new Usuario(nome,email,encode);
    }
}
