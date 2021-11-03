package com.davidvinson.pokedex.model

import javax.persistence.*

@Entity
@Table(name = "trainer")
class TrainerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val username: String,
    val password: String
    )

// Trainer Response Model
data class TrainerResponse(
    val id: Int,
    val username: String
    )

// TrainerEntity to Response Model
fun TrainerEntity.toResponse(): TrainerResponse {
    return TrainerResponse(
        id = id,
        username = username
    )
}