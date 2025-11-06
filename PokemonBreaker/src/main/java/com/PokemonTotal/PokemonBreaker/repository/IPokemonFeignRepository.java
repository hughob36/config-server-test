package com.PokemonTotal.PokemonBreaker.repository;

import com.PokemonTotal.PokemonBreaker.dto.PokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemon", url = "https://pokeapi.co/api/v2")
public interface IPokemonFeignRepository {

    @GetMapping("/pokemon/{id}")
    public PokemonDTO getPokemon(@PathVariable int id);

}
