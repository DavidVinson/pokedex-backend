package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository : JpaRepository<TypeEntity, Int> {}