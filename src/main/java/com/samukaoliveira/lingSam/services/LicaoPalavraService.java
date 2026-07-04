package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.LicaoPalavra;
import com.samukaoliveira.lingSam.repositories.LicaoPalavraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicaoPalavraService {

    private final LicaoPalavraRepository repository;

    public List<LicaoPalavra> listar() {
        return repository.findAll();
    }

    public LicaoPalavra buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado."));
    }

    public LicaoPalavra salvar(LicaoPalavra licaoPalavra) {
        return repository.save(licaoPalavra);
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

}