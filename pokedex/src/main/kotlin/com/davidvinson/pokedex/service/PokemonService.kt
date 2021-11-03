package com.davidvinson.pokedex.service

import com.davidvinson.pokedex.model.PokemonDataEntity
import com.davidvinson.pokedex.model.PokemonDataRepository
import com.davidvinson.pokedex.model.PokemonEntity
import com.davidvinson.pokedex.repository.PokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class PokemonService(val pokemonRepository: PokemonRepository) {

    fun getAllPokemon(): List<PokemonEntity> = pokemonRepository.findAll()

    fun getPokemonById(pokemonId: Int): PokemonEntity = pokemonRepository.findById(pokemonId)
        .orElseThrow { PokemonNotFoundException(HttpStatus.NOT_FOUND, "No matching pokemon was found")}
}

@Service
class PokemonDataServiceTest(val pokemonDataRepository: PokemonDataRepository) {
    fun listAllPokemon(): List<PokemonDataEntity> = pokemonDataRepository.findAll()

    fun getPokemonByIdTest(pokemonId: Int): PokemonDataEntity = pokemonDataRepository.findById(pokemonId)
        .orElseThrow { PokemonNotFoundException(HttpStatus.NOT_FOUND, "No matching pokemon was found")}
}



