package com.samukaoliveira.lingSam.controllers;

import com.samukaoliveira.lingSam.dto.PalavraStatusDTO;
import com.samukaoliveira.lingSam.services.PalavraUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palavras")
@RequiredArgsConstructor
public class PalavraController {

    private final PalavraUsuarioService service;

    @PostMapping("/status")
    public ResponseEntity<Void> atualizarStatus(
            @RequestBody PalavraStatusDTO dto,
            Authentication authentication){

        service.atualizarStatus(
                authentication.getName(),
                dto.getPalavra(),
                dto.getStatus());

        return ResponseEntity.ok().build();

    }

}