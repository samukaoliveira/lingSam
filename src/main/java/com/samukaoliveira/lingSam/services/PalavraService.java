package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.Palavra;
import com.samukaoliveira.lingSam.repositories.PalavraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PalavraService {

    private final PalavraRepository repository;

    public List<Palavra> listar() {
        return repository.findAll();
    }

    public Palavra buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Palavra não encontrada."));
    }

    public Palavra salvar(Palavra palavra) {
        return repository.save(palavra);
    }

    public Palavra atualizar(Long id, Palavra palavra) {

        Palavra existente = buscarPorId(id);

        existente.setLemma(palavra.getLemma());
        existente.setTraducao(palavra.getTraducao());
        existente.setDefinicaoCurta(palavra.getDefinicaoCurta());
        existente.setClasseGramatical(palavra.getClasseGramatical());
        existente.setPronuncia(palavra.getPronuncia());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

}