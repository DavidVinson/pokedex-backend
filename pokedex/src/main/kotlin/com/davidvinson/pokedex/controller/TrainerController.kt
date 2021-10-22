package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.TrainerEntity
import com.davidvinson.pokedex.model.TrainerResponse
import com.davidvinson.pokedex.model.toResponse
import com.davidvinson.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TrainerController(val trainerService: TrainerService) {

    @GetMapping("/api/trainer")
    fun getAllTrainer(): ResponseEntity<List<TrainerEntity>> {
        return ResponseEntity.ok(trainerService.getAllTrainer())
    }

    @GetMapping("/api/trainer/{id}")
    fun getTrainerById(@PathVariable("id") trainerId: Int): ResponseEntity<TrainerResponse> {
        return ResponseEntity.ok(trainerService.getTrainerById(trainerId).toResponse())
    }
}


/*
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
 */