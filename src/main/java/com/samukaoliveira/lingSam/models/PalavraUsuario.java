package com.samukaoliveira.lingSam.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PalavraUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Palavra palavra;

    private Integer visualizacoes;

    private Integer acertos;

    private Integer erros;

    private Integer score;

    @Enumerated(EnumType.STRING)
    private StatusPalavra status;

    private LocalDateTime primeiraVisualizacao;

    private LocalDateTime ultimaVisualizacao;
}
