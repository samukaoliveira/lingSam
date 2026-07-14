package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.Licao;
import com.samukaoliveira.lingSam.repositories.LicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicaoService {

    @Autowired
    private ImportadorTextoService importadorTextoService;

    private final LicaoRepository repository;

    public List<Licao> listar() {
        return repository.findAll();
    }

    public Licao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lição não encontrada."));
    }

    public Licao salvar(Licao licao){

        Licao salva = repository.save(licao);

        importadorTextoService.processar(salva);

        return salva;

    }

    public Licao atualizar(Long id, Licao licao) {

        Licao existente = buscarPorId(id);

        existente.setTitulo(licao.getTitulo());
        existente.setTextoOriginal(licao.getTextoOriginal());
        existente.setDificuldade(licao.getDificuldade());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }

    public List<Licao> listarRecentes(int quantidade){

        return repository.findAllByOrderByIdDesc(
                PageRequest.of(0, quantidade));

    }

}