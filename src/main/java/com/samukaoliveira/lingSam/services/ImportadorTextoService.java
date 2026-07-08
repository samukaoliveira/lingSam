package com.samukaoliveira.lingSam.services;

import com.samukaoliveira.lingSam.dto.PalavraViewDTO;
import com.samukaoliveira.lingSam.models.*;
import com.samukaoliveira.lingSam.repositories.PalavraRepository;
import com.samukaoliveira.lingSam.repositories.PalavraUsuarioRepository;
import com.samukaoliveira.lingSam.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportadorTextoService {

    private final TextoParserService parser;

    private final ContadorPalavrasService contador;

    private final PalavraRepository palavraRepository;

    private final PalavraUsuarioRepository palavraUsuarioRepository;

    private final UsuarioService usuarioService;

    public void processar(Licao licao) {

        Usuario usuario = usuarioService.obterUsuarioPadrao();

        List<PalavraViewDTO> tokens =
                parser.extrairPalavras(licao.getTextoOriginal());

        for (PalavraViewDTO token : tokens) {

            if(token.getTipo() != TipoToken.PALAVRA){
                continue;
            }

            Palavra palavra =
                    salvarPalavraSeNaoExistir(
                            normalizar(token.getTexto()));

            salvarPalavraUsuarioSeNaoExistir(
                    usuario,
                    palavra);

        }

    }

    private void salvarPalavraUsuarioSeNaoExistir(
            Usuario usuario,
            Palavra palavra) {

        palavraUsuarioRepository
                .findByUsuarioAndPalavra(usuario, palavra)
                .orElseGet(() -> {

                    PalavraUsuario pu = new PalavraUsuario();

                    pu.setUsuario(usuario);

                    pu.setPalavra(palavra);

                    pu.setStatus(StatusPalavra.NOVA);

                    pu.setVisualizacoes(0);

                    pu.setAcertos(0);

                    pu.setErros(0);

                    pu.setScore(0);

                    return palavraUsuarioRepository.save(pu);

                });

    }

    private Palavra salvarPalavraSeNaoExistir(String lemma) {

        return  palavraRepository.findByLemma(lemma)
                .orElseGet(() -> {

                    Palavra palavra = new Palavra();

                    palavra.setLemma(lemma);

                    palavra.setTraducao(null);
                    palavra.setDefinicaoCurta(null);
                    palavra.setClasseGramatical(null);
                    palavra.setPronuncia(null);

                    return palavraRepository.save(palavra);

                });

    }

    private String normalizar(String palavra) {

        return palavra
                .trim()
                .toLowerCase();

    }

}