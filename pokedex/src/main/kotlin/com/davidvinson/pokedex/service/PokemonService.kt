package com.davidvinson.pokedex.service

import com.davidvinson.pokedex.model.PokemonEntity
import com.davidvinson.pokedex.repository.PokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class PokemonService(val pokemonRepository: PokemonRepository) {

    fun getAllPokemon(): List<PokemonEntity> = pokemonRepository.findAll()

    fun getPokemonById(pokemonId: Int): PokemonEntity = pokemonRepository.findById(pokemonId)
        .orElseThrow { PokemonNotFoundException(HttpStatus.NOT_FOUND, "No matching pokemon was found")}

}



