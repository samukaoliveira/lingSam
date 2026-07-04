package com.samukaoliveira.lingSam.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContadorPalavrasService {

    public Map<String,Integer> contar(List<String> palavras){

        Map<String,Integer> mapa =
                new LinkedHashMap<>();

        for(String palavra : palavras){

            mapa.put(
                    palavra,
                    mapa.getOrDefault(palavra,0)+1
            );

        }

        return mapa;

    }

}