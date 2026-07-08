package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.Usuario;
import com.samukaoliveira.lingSam.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Optional<Usuario> buscarPorUsername(String username){

        return repository.findByUsername(username);

    }

    public Usuario obterUsuarioPadrao() {

        return repository.findByUsername("admin")
                .orElseThrow(() ->
                        new IllegalStateException("Usuário admin não encontrado."));
    }

}
