package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Entidades.Usuario;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    Optional<Usuario> findByEmail(String username);

    @Query(value = " INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES(?, ?)",
    nativeQuery = true)
    Usuario darPermissaoDeUsuario(Long usuarioId , Long perfilId);
}
