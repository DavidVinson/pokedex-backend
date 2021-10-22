package com.davidvinson.pokedex.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "pokemon")
class PokemonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val image: String,
    val height: Double,
    val weight: Double,
    val genus: String,
    val description: String,

    @ManyToMany
    val types: List<TypeEntity>,

    @ManyToMany
    val abilities: List<AbilityEntity>,

    @ManyToMany
    val eggGroups: List<EggGroupEntity>,

    @OneToOne
    val stats: StatEntity

)

@Entity
@Table(name = "type")
class TypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String
)

@Entity
@Table(name = "ability")
class AbilityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String
)

@Entity
@Table(name = "eggGroup")
class EggGroupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String
)

@Entity
@Table(name = "stat")
class StatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val speed: Int,
    val specialAttack: Int,
    val specialDefence: Int
)


// Pokemon Response Model
data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<String>,
    val height: Double,
    val weight: Double,
    val abilities: List<String>,

    @JsonProperty("egg_groups")
    val eggGroups: List<String>,
    val stats: Stat,
    val genus: String,
    val description: String
    )

// Pokemon List Response Model
data class PokemonListResponse(
    val id: Int,
    val name: String,
    val types: List<String>
)

// Stat Model
data class Stat(
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val speed: Int,

    @JsonProperty("special_attack")
    val specialAttack: Int,

    @JsonProperty("special_defence")
    val specialDefence: Int
)

// PokemonEntity to Response Model
fun PokemonEntity.toResponse(): PokemonResponse {
    return PokemonResponse(
        id = id,
        name = name,
        types = types.map { type -> type.name },
        height = height,
        weight = weight,
        abilities = abilities.map { ability -> ability.name},
        eggGroups = eggGroups.map { egg -> egg.name },
        stats = stats.toModel(),
        genus = genus,
        description = description

    )
}

// PokemonEntity to List Response Model
fun PokemonEntity.toListResponse(): PokemonListResponse {
    return PokemonListResponse(
        id = id,
        name = name,
        types = types.map { type -> type.name },
    )
}

// StatEntity to Stat Model
fun StatEntity.toModel(): Stat {
    return Stat(
        hp = hp,
        attack = attack,
        defence = defence,
        speed = speed,
        specialAttack = specialAttack,
        specialDefence = specialDefence
    )
}





