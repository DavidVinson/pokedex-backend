package com.davidvinson.pokedex.repository

import com.davidvinson.pokedex.model.TrainerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository : JpaRepository<TrainerEntity, Int> {}

