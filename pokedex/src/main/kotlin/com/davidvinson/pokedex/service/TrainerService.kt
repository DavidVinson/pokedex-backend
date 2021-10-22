package com.davidvinson.pokedex.service

import com.davidvinson.pokedex.model.TrainerEntity
import com.davidvinson.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TrainerService(val trainerRepository: TrainerRepository) {

    fun getAllTrainer(): List<TrainerEntity> = trainerRepository.findAll()

    fun getTrainerById(trainerId: Int): TrainerEntity = trainerRepository.findById(trainerId)
        .orElseThrow { TrainerNotFoundException(HttpStatus.NOT_FOUND, "No matching trainer was found")}
}
