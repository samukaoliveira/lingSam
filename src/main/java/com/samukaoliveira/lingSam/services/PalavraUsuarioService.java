package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.Palavra;
import com.samukaoliveira.lingSam.models.PalavraUsuario;
import com.samukaoliveira.lingSam.models.StatusPalavra;
import com.samukaoliveira.lingSam.models.Usuario;
import com.samukaoliveira.lingSam.repositories.PalavraRepository;
import com.samukaoliveira.lingSam.repositories.PalavraUsuarioRepository;
import com.samukaoliveira.lingSam.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PalavraUsuarioService {

    private final PalavraUsuarioRepository repository;
    private final PalavraRepository palavraRepository;
    private final UsuarioRepository usuarioRepository;

    public List<PalavraUsuario> listar() {
        return repository.findAll();
    }

    public PalavraUsuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado."));
    }

    public PalavraUsuario salvar(PalavraUsuario palavraUsuario) {
        return repository.save(palavraUsuario);
    }

    public PalavraUsuario atualizar(Long id, PalavraUsuario palavraUsuario) {

        PalavraUsuario existente = buscarPorId(id);

        existente.setStatus(palavraUsuario.getStatus());
        existente.setVisualizacoes(palavraUsuario.getVisualizacoes());
        existente.setAcertos(palavraUsuario.getAcertos());
        existente.setErros(palavraUsuario.getErros());
        existente.setScore(palavraUsuario.getScore());
        existente.setUltimaVisualizacao(palavraUsuario.getUltimaVisualizacao());

        return repository.save(existente);
    }

    public void atualizarStatus(String username,
                                String lemma,
                                StatusPalavra status){

        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Palavra palavra = palavraRepository
                .findByLemma(lemma)
                .orElseThrow(() -> new EntityNotFoundException("Palavra não encontrada"));

        PalavraUsuario palavraUsuario = repository
                .findByUsuarioAndPalavra(usuario, palavra)
                .orElseThrow(() -> new EntityNotFoundException("Palavra do usuário não encontrada"));

        palavraUsuario.setStatus(status);

        repository.save(palavraUsuario);

    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

}