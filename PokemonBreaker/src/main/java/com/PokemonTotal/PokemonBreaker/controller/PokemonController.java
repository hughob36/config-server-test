package com.PokemonTotal.PokemonBreaker.controller;

import com.PokemonTotal.PokemonBreaker.dto.PokemonDTO;
import com.PokemonTotal.PokemonBreaker.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemon/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public PokemonDTO getPokemon(@PathVariable int id) {
        return pokemonService.getPokemon(id);
    }
}
