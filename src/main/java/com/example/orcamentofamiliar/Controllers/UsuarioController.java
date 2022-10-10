package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.UsuarioDto;
import com.example.orcamentofamiliar.Controllers.Forms.UsuarioForm;
import com.example.orcamentofamiliar.Entidades.Usuario;
import com.example.orcamentofamiliar.Repository.PerfilRepository;
import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import com.example.orcamentofamiliar.service.UsuarioService;
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
    private UsuarioService service;


    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm,
                                                       UriComponentsBuilder uriComponentsBuilder)  {

        UsuarioDto usuario = service.cadastrar(usuarioForm);

        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }


}
