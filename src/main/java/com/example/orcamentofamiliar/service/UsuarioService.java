package com.example.orcamentofamiliar.service;

import com.example.orcamentofamiliar.Controllers.Dtos.UsuarioDto;
import com.example.orcamentofamiliar.Controllers.Forms.UsuarioForm;
import com.example.orcamentofamiliar.Entidades.Perfil;
import com.example.orcamentofamiliar.Entidades.Usuario;
import com.example.orcamentofamiliar.Repository.PerfilRepository;
import com.example.orcamentofamiliar.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    private Perfil perfil;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private ModelMapper modelMapper;


    public String criptografia(UsuarioForm form) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(form.getSenha());

    }

    public void darPermissaoParaUsuario(Long idUsuario) {


        repository.darPermissaoDeUsuario(idUsuario, 1L);
    }

    public UsuarioDto cadastrar(UsuarioForm form) {


        isRepeat(form);
        Usuario usuario = modelMapper.map(form, Usuario.class);
        String criptografia = criptografia(form);
        usuario.setSenha(criptografia);
        repository.save(usuario);
        darPermissaoParaUsuario(usuario.getId());

        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public void isRepeat(UsuarioForm form) {
        boolean present = repository.findByEmail(form.getEmail()).isPresent();

        if (present) {
            throw new EntityExistsException();
        }
    }


}
