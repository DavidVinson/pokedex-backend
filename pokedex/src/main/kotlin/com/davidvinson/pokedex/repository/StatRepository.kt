package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.StatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatRepository : JpaRepository<StatEntity, Int> {}