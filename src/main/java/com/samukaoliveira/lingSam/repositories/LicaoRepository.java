package com.samukaoliveira.lingSam.repositories;

import com.samukaoliveira.lingSam.models.Licao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicaoRepository extends JpaRepository<Licao, Long> {

    List<Licao> findAllByOrderByIdDesc(Pageable pageable);
}



