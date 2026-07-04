package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.models.Licao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportadorTextoService {

    private final TextoParserService parser;

    private final ContadorPalavrasService contador;

    public void processar(Licao licao){

        var palavras = parser.extrairPalavras(
                licao.getTextoOriginal()
        );

        var mapa = contador.contar(palavras);

        mapa.forEach((palavra, quantidade) -> {

            System.out.println(
                    palavra + " -> " + quantidade
            );

        });

    }

}