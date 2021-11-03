package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.*
//import com.davidvinson.pokedex.service.PokemonDataServiceTest
import com.davidvinson.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonController(
    val pokemonService: PokemonService,
    val pokemonSeedDataService: PokemonSeedDataService,
//    val pokemonDataServiceTest: PokemonDataServiceTest
    ) {

    @GetMapping("/api/pokemon")
    fun getAllPokemon(): ResponseEntity<List<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon().map {pokemon -> pokemon.toListResponse()})
    }

    @GetMapping("/api/pokemon/{id}")
    fun getPokemonById(@PathVariable("id") pokemonId: Int): ResponseEntity<PokemonResponse> {
        return ResponseEntity.ok(pokemonService.getPokemonById(pokemonId).toResponse())
    }

    // TEST ENDPOINTS
    @GetMapping("/api/pokemon/initialize")
    fun seedDatabase() {
        pokemonSeedDataService.databaseInitializer()
        println("successful save")
    }

//    @GetMapping("/api/pokemon/test/{id}")
//    fun getPokemonByIdTest(@PathVariable("id") pokemonId: Int): PokemonResponseTest {
//        return pokemonDataServiceTest.getPokemonByIdTest(pokemonId).toResponseTest()
//    }
//
//    @GetMapping("/api/pokemon/ListTest")
//    fun getAllPokemonListTest(): List<PokemonListResponseTest> {
//        return pokemonDataServiceTest.listAllPokemon().map {pokemon -> pokemon.toListResponse()}
//    }
}

