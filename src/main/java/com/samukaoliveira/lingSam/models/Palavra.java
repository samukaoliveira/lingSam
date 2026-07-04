package com.samukaoliveira.lingSam.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "lemma")
)
public class Palavra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lemma;

    private String traducao;

    private String definicaoCurta;

    private String classeGramatical;

    private String pronuncia;

}
