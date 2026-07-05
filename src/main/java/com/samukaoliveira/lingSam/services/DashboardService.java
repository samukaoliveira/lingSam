package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.dto.DashboardDTO;
import com.samukaoliveira.lingSam.models.StatusPalavra;
import com.samukaoliveira.lingSam.models.Usuario;
import com.samukaoliveira.lingSam.repositories.PalavraUsuarioRepository;
import com.samukaoliveira.lingSam.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PalavraUsuarioRepository repository;
    private final UsuarioRepository usuarioRepository;

    private Usuario usuarioLogado() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return usuarioRepository.findByUsername(username)
                .orElseThrow();
    }

    public DashboardDTO obterResumo() {

        Usuario usuario = usuarioLogado();

        return new DashboardDTO(

                repository.countByUsuarioAndStatus(
                        usuario,
                        StatusPalavra.NOVA),

                repository.countByUsuarioAndStatus(
                        usuario,
                        StatusPalavra.FAMILIAR),

                repository.countByUsuarioAndStatus(
                        usuario,
                        StatusPalavra.APRENDENDO),

                repository.countByUsuarioAndStatus(
                        usuario,
                        StatusPalavra.DOMINADA)

        );

    }

}