package com.samukaoliveira.lingSam.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LicaoPalavra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Licao licao;

    @ManyToOne
    private Palavra palavra;

    private Integer ocorrencias;
}
