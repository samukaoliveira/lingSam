package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.dto.PalavraViewDTO;
import com.samukaoliveira.lingSam.models.Licao;
import com.samukaoliveira.lingSam.models.Palavra;
import com.samukaoliveira.lingSam.models.PalavraUsuario;
import com.samukaoliveira.lingSam.models.StatusPalavra;
import com.samukaoliveira.lingSam.models.TipoToken;
import com.samukaoliveira.lingSam.models.Usuario;
import com.samukaoliveira.lingSam.repositories.PalavraRepository;
import com.samukaoliveira.lingSam.repositories.PalavraUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeitorLicaoService {

    private final TextoParserService parser;

    private final PalavraRepository palavraRepository;

    private final PalavraUsuarioRepository palavraUsuarioRepository;

    private final UsuarioService usuarioService;

    public List<PalavraViewDTO> montarTexto(Licao licao) {

        Usuario usuario = usuarioService.obterUsuarioPadrao();

        List<PalavraViewDTO> tokens =
                parser.extrairPalavras(licao.getTextoOriginal());

        List<PalavraViewDTO> resultado = new ArrayList<>();

        for (PalavraViewDTO token : tokens) {

            if(token.getTipo() != TipoToken.PALAVRA){

                resultado.add(token);

                continue;

            }

            String lemma = normalizar(token.getTexto());

            Palavra palavra = palavraRepository
                    .findByLemma(lemma)
                    .orElse(null);

            if (palavra == null) {

                resultado.add(
                        new PalavraViewDTO(
                                null,
                                token.getTexto(),
                                StatusPalavra.NOVA,
                                TipoToken.PALAVRA
                        )
                );

                continue;
            }

            PalavraUsuario palavraUsuario =
                    palavraUsuarioRepository
                            .findByUsuarioAndPalavra(usuario, palavra)
                            .orElse(null);

            resultado.add(

                    new PalavraViewDTO(

                            palavra.getId(),

                            token.getTexto(),

                            palavraUsuario != null
                                    ? palavraUsuario.getStatus()
                                    : StatusPalavra.NOVA,

                            TipoToken.PALAVRA

                    )

            );

        }

        resultado.forEach(t ->
                System.out.println(
                        t.getTexto() + " | " +
                                t.getTipo() + " | " +
                                t.getStatus()
                )
        );

        return resultado;

    }

    private String normalizar(String texto) {

        texto = Normalizer.normalize(
                texto,
                Normalizer.Form.NFD);

        texto = texto.replaceAll("\\p{M}", "");

        return texto
                .toLowerCase()
                .trim();

    }

}