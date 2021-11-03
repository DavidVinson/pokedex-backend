package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.*
import com.davidvinson.pokedex.service.PokemonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(
    val pokemonService: PokemonService,
    val pokemonSeedDataService: PokemonSeedDataService,
    ) {

    @GetMapping("/api/pokemon")
    fun getAllPokemon(@RequestParam(required = false, defaultValue = "") name: String, pageable: Pageable): ResponseEntity<Page<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon(pageable, name).map {pokemon -> pokemon.toListResponse()})
    }

    @GetMapping("/api/pokemon/{id}")
    fun getPokemonById(@PathVariable("id") pokemonId: Int): ResponseEntity<PokemonResponse> {
        return ResponseEntity.ok(pokemonService.getPokemonById(pokemonId).toResponse())
    }

    // SEED DB ENDPOINT
    @GetMapping("/api/pokemon/initialize")
    fun seedDatabase() {
        pokemonSeedDataService.databaseInitializer()
        println("successful save")
    }
}

