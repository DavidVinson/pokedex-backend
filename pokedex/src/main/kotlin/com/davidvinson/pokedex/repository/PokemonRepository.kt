package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository



@Repository
interface PokemonRepository : JpaRepository<PokemonEntity, Int> {}

