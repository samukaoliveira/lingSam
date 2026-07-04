package com.samukaoliveira.lingSam.repositories;

import com.samukaoliveira.lingSam.models.Palavra;
import com.samukaoliveira.lingSam.models.PalavraUsuario;
import com.samukaoliveira.lingSam.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PalavraUsuarioRepository extends JpaRepository<PalavraUsuario, Long> {

    Optional<PalavraUsuario> findByUsuarioAndPalavra(
            Usuario usuario,
            Palavra palavra);
}



