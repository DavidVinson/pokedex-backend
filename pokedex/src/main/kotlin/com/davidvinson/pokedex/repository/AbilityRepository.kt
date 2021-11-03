package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.AbilityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityRepository : JpaRepository<AbilityEntity, Int> {}
