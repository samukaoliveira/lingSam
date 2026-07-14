package com.samukaoliveira.lingSam.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Licao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private NivelDificuldade dificuldade;

    @Column(columnDefinition = "TEXT")
    private String textoOriginal;

    private LocalDateTime dataImportacao;
}
