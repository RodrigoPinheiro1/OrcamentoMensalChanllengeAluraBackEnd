package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.UsuarioDto;
import com.example.orcamentofamiliar.Controllers.Forms.UsuarioForm;
import com.example.orcamentofamiliar.Entidades.Usuario;
import com.example.orcamentofamiliar.Repository.PerfilRepository;
import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;



    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm,
                                                       UriComponentsBuilder uriComponentsBuilder)  {

        if (usuarioForm.isRepeat(usuarioRepository)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario com Email em uso");
        }

        Usuario usuario = usuarioForm.cadastrar();
        usuarioRepository.save(usuario);

        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }


}
