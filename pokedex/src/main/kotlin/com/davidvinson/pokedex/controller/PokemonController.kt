package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.*
import com.davidvinson.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(val pokemonService: PokemonService) {

    @GetMapping("/api/pokemon")
    fun getAllPokemon(): ResponseEntity<List<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon().map {pokemon -> pokemon.toListResponse()})
    }

    @GetMapping("/api/pokemon/{id}")
    fun getPokemonById(@PathVariable("id") pokemonId: Int): ResponseEntity<PokemonResponse> {
        return ResponseEntity.ok(pokemonService.getPokemonById(pokemonId).toResponse())

    }

//    @GetMapping("/api/pokemon/{id}")
//    fun getPokemonById(@PathVariable("id") pokemonId: Int): PokemonResponse {
//        return pokemonService.getPokemonById(pokemonId).toResponse()
//
//    }

}

