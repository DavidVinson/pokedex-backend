package com.davidvinson.pokedex.controller

import com.davidvinson.pokedex.model.TrainerEntity
import com.davidvinson.pokedex.model.TrainerResponse
import com.davidvinson.pokedex.model.toResponse
import com.davidvinson.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TrainerController(val trainerService: TrainerService) {

    @GetMapping("/api/trainers")
    fun getAllTrainer(): ResponseEntity<List<TrainerEntity>> {
        return ResponseEntity.ok(trainerService.getAllTrainer())
    }

    @GetMapping("/api/trainers/{id}")
    fun getTrainerById(@PathVariable("id") trainerId: Int): ResponseEntity<TrainerResponse> {
        return ResponseEntity.ok(trainerService.getTrainerById(trainerId).toResponse())
    }
}
