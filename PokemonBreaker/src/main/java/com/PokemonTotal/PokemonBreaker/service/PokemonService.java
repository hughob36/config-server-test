package com.PokemonTotal.PokemonBreaker.service;

import com.PokemonTotal.PokemonBreaker.dto.PokemonDTO;
import com.PokemonTotal.PokemonBreaker.repository.IPokemonFeignRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    @Autowired
    private IPokemonFeignRepository pokemonFeignRepository;

    @CircuitBreaker(name = "pokemon", fallbackMethod = "getPokemonF")
    @Retry(name = "pokemon")
    public PokemonDTO getPokemon(int id){

       //fallo();

        return pokemonFeignRepository.getPokemon(id);
    }

    public void fallo() {
        throw new IllegalArgumentException("falladoTotal");
    }


    public PokemonDTO getPokemonF(int id,Throwable throwable) {
        return new PokemonDTO(12,"fallado");
    }

}
