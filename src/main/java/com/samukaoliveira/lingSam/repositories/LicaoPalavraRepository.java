package com.samukaoliveira.lingSam.repositories;

import com.samukaoliveira.lingSam.models.Licao;
import com.samukaoliveira.lingSam.models.LicaoPalavra;
import com.samukaoliveira.lingSam.models.Palavra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicaoPalavraRepository extends JpaRepository<LicaoPalavra, Long> {

    Optional<LicaoPalavra> findByLicaoAndPalavra(
            Licao licao,
            Palavra palavra);
}



