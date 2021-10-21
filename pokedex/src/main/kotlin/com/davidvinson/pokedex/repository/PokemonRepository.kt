package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository



@Repository
interface PokemonRepository : JpaRepository<PokemonEntity, Int> {}

//@Repository
//interface PokemonTypeRepository : JpaRepository<PokemonTypeEntity, Int> {}


////Example
//interface UserRepository : JpaRepository<User?, Long?> {
//    fun findByLastname(lastname: String?): List<User?>?
//    fun findByEmailAddress(emailAddress: String?): User?
//}