package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.UsuarioDto;
import com.example.orcamentofamiliar.Controllers.Forms.UsuarioForm;
import com.example.orcamentofamiliar.Entidades.Usuario;
import com.example.orcamentofamiliar.Repository.PerfilRepository;
import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm,
                                                       UriComponentsBuilder uriComponentsBuilder)  {

        Usuario usuario = usuarioForm.cadastrar();
        usuarioRepository.save(usuario);

        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }


}
