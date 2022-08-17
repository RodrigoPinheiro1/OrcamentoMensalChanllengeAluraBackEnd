package com.example.orcamentofamiliar.Controllers.Forms;

import com.example.orcamentofamiliar.Entidades.Perfil;
import com.example.orcamentofamiliar.Entidades.Usuario;
import com.example.orcamentofamiliar.Repository.PerfilRepository;
import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UsuarioForm {

    @NotNull
    private String nome;

    @NotNull
    private String senha;
    @NotNull
    private String email;

    public Usuario cadastrar() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(senha);
        return new Usuario(nome, email, encode);
    }
}
