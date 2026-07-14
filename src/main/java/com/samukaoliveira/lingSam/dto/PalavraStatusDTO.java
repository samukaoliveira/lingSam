package com.samukaoliveira.lingSam.dto;

import com.samukaoliveira.lingSam.models.StatusPalavra;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PalavraStatusDTO {

    private String palavra;

    private StatusPalavra status;

}