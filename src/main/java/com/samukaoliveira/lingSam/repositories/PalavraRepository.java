package com.samukaoliveira.lingSam.repositories;

import com.samukaoliveira.lingSam.models.Licao;
import com.samukaoliveira.lingSam.models.Palavra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PalavraRepository extends JpaRepository<Palavra, Long> {

    Optional<Palavra> findByLemma(String lemma);
}



