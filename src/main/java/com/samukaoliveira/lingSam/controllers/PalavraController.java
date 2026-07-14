package com.samukaoliveira.lingSam.controllers;

import com.samukaoliveira.lingSam.dto.PalavraStatusDTO;
import com.samukaoliveira.lingSam.services.PalavraUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status")
    @ResponseBody
    public PalavraStatusDTO buscarStatus(@RequestParam String palavra){

        return service.buscarStatus(palavra);

    }

}