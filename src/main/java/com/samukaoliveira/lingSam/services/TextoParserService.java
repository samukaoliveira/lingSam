package com.samukaoliveira.lingSam.services;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class TextoParserService {

    private static final Pattern PONTUACAO =
            Pattern.compile("[^a-zA-Z']");

    public List<String> extrairPalavras(String texto){

        if(texto == null || texto.isBlank())
            return Collections.emptyList();

        texto = texto.toLowerCase();

        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);

        texto = texto.replaceAll("\\p{M}", "");

        texto = PONTUACAO
                .matcher(texto)
                .replaceAll(" ");

        return Arrays.stream(texto.split("\\s+"))
                .filter(s -> !s.isBlank())
                .toList();

    }

}