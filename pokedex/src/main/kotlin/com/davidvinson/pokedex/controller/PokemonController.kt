package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.PokemonEntity
import com.davidvinson.pokedex.model.PokemonResponse
import com.davidvinson.pokedex.model.toResponse
import com.davidvinson.pokedex.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(val pokemonService: PokemonService) {

    @GetMapping("/api/pokemon")
    fun getAllPokemon(): List<PokemonEntity> {
        return pokemonService.getAllPokemon()
    }

    @GetMapping("/api/pokemon/{id}")
    fun getPokemonById(@PathVariable("id") pokemonId: Int): PokemonResponse {
        return pokemonService.getPokemonById(pokemonId).toResponse()

    }

//    @GetMapping("/api/pokemon/{id}")
//    fun getPokemonById(@PathVariable("id") pokemonId: Int): ResponseEntity<PokemonResponse> {
//       return ResponseEntity<PokemonResponse>(HttpStatus.FOUND).statusCode
//
//    }

//    @GetMapping("/api/pokemon/{name}")
//    fun getPokemonByName(@RequestParam("name") name: String): Pokemon = pokemonService.getPokemonName(name)

}

