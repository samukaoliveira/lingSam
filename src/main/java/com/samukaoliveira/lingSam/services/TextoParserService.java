package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.dto.PalavraViewDTO;
import com.samukaoliveira.lingSam.models.TipoToken;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextoParserService {

    private static final Pattern TOKEN =
            Pattern.compile("[A-Za-z]+(?:'[A-Za-z]+)?| |\n|[.,!?;:()\"-]");

    public List<PalavraViewDTO> extrairPalavras(String texto) {

        if (texto == null || texto.isBlank()) {
            return Collections.emptyList();
        }

        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{M}", "");

        List<PalavraViewDTO> resultado = new ArrayList<>();

        StringBuilder palavra = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            // Faz parte de uma palavra
            if (Character.isLetter(c) || c == '\'') {

                palavra.append(c);
                continue;
            }

            // Terminou uma palavra
            if (!palavra.isEmpty()) {

                resultado.add(
                        new PalavraViewDTO(
                                null,
                                palavra.toString(),
                                null,
                                TipoToken.PALAVRA
                        )
                );

                palavra.setLength(0);

            }

            // Espaço
            if (c == ' ') {

                resultado.add(
                        new PalavraViewDTO(
                                null,
                                " ",
                                null,
                                TipoToken.ESPACO
                        )
                );

                continue;

            }

            // Quebra de linha (\n)
            if (c == '\n') {

                resultado.add(
                        new PalavraViewDTO(
                                null,
                                "\n",
                                null,
                                TipoToken.QUEBRA_LINHA
                        )
                );

                continue;

            }

            // Ignora o \r do Windows (CRLF)
            if (c == '\r') {
                continue;
            }

            // Qualquer outro caractere é pontuação
            resultado.add(
                    new PalavraViewDTO(
                            null,
                            String.valueOf(c),
                            null,
                            TipoToken.PONTUACAO
                    )
            );

        }

        // Última palavra do texto
        if (!palavra.isEmpty()) {

            resultado.add(
                    new PalavraViewDTO(
                            null,
                            palavra.toString(),
                            null,
                            TipoToken.PALAVRA
                    )
            );

        }

        return resultado;

    }

}