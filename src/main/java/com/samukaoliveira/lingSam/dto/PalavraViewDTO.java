package com.samukaoliveira.lingSam.dto;

import com.samukaoliveira.lingSam.models.StatusPalavra;
import com.samukaoliveira.lingSam.models.TipoToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PalavraViewDTO {

    private Long id;

    private String texto;

    private StatusPalavra status;

    private TipoToken tipo;

}