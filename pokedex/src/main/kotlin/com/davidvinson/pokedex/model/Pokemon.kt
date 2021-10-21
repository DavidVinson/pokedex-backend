package com.davidvinson.pokedex.model

import javax.persistence.*


@Entity
@Table(name = "pokemon")
class PokemonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val image: String,

    @ManyToMany
    val types: List<PokemonTypeEntity>
)

@Entity
@Table(name = "type")
class PokemonTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String
)


// Response Model
data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<String>
    )

fun PokemonEntity.toResponse(): PokemonResponse {
    return PokemonResponse(
        id = this.id,
        name = this.name,
        types = this.types.map { type -> type.name }
    )
}





