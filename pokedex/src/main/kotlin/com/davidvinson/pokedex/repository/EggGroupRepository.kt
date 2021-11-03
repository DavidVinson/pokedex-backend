package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.EggGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EggGroupRepository : JpaRepository<EggGroupEntity, Int> {}
