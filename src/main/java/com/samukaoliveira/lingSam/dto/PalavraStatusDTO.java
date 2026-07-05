package com.samukaoliveira.lingSam.dto;

import com.samukaoliveira.lingSam.models.StatusPalavra;
import lombok.Data;

@Data
public class PalavraStatusDTO {

    private String palavra;

    private StatusPalavra status;

}