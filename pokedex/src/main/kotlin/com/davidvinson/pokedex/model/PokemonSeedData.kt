package com.davidvinson.pokedex.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*


@Service
class PokemonSeedDataService(
    val pokemonDataRepository: PokemonDataRepository,
    val pokemonDataTypeRepository: PokemonDataTypeRepository,
    val pokemonDataAbilityRepository: PokemonDataAbilityRepository,
    val pokemonDataEggGroupEntityRepository: PokemonDataEggGroupRepository,
    val pokemonDataStatRepository: PokemonDataStatRepository
    ) {

    private val filePath = "src/main/resources/rawData5-copy.csv"

    fun databaseInitializer() {

        csvReader().open(filePath) {
            readAllWithHeaderAsSequence().forEach { row ->
                val id: String = row["id"].toString() // row.get("id")
                val name: String = row["name"].toString()
                val height: String = row["height"].toString()
                val weight: String = row["weight"].toString()
                val genus: String = row["genus"].toString()
                val description: String = row["description"].toString()
                val types: String = row["types"].toString()
                val abilities: String = row["abilities"].toString()
                val eggGroups: String = row["egg_groups"].toString()
                val stats: String = row["stats"].toString()

                //Types
                //clean types string by removing "[]" from the string
                val newTypes: String = types.drop(1).dropLast(1)
                //clean types by creating a list of types ["grass", "poison"]
                val newTypes2: List<String> = newTypes.split(",")
                //create a unique set of pokemon types
                val newTypesSet: MutableSet<String> = mutableSetOf()
                newTypes2.forEach { type -> newTypesSet.add(type.trim())}

                //create the list of types for each pokemon
                val myPokeTypes: MutableList<PokemonDataTypeEntity> = mutableListOf()
                newTypesSet.forEach { item ->
                    val pokemonType: PokemonDataTypeEntity = PokemonDataTypeEntity(
                        name = item
                    )
                    myPokeTypes.add(pokemonType)
                    saveTypeToDB(pokemonType)

                }

                //Abilities
                //clean abilities string by removing "[]" from the string
                val newAbilities: String = abilities.drop(1).dropLast(1)
                //clean types by creating a list of abilities ["chlorophyll", "overgrow"]
                val newAbilities2: List<String> = newAbilities.split(",")
                //create a unique set of pokemon abilities
                val newAbilitiesSet: MutableSet<String> = mutableSetOf()
                newAbilities2.forEach { ability -> newAbilitiesSet.add(ability.trim())}

                //create the list of abilities for each pokemon
                val myPokeAbilities: MutableList<PokemonDataAbilityEntity> = mutableListOf()
                newAbilitiesSet.forEach { item ->
                    val pokemonAbility: PokemonDataAbilityEntity = PokemonDataAbilityEntity(
                        name = item
                    )
                    myPokeAbilities.add(pokemonAbility)
                    saveAbilityToDB(pokemonAbility)

                }

                //Egg Groups
                //clean egg group string by removing "[]" from the string
                val newEggGroups: String = eggGroups.drop(1).dropLast(1)
                //clean egg groups by creating a list of egg groups ["plant", "monster"]
                val newEggGroups2: List<String> = newEggGroups.split(",")
                //create a unique set of pokemon egg groups
                val newEggGroupsSet: MutableSet<String> = mutableSetOf()
                newEggGroups2.forEach { eggGroup -> newEggGroupsSet.add(eggGroup.trim())}

                //create the list of egg groups for each pokemon
                val myPokeEggGroups: MutableList<PokemonDataEggGroupEntity> = mutableListOf()
                newEggGroupsSet.forEach { item ->
                    val pokemonEggGroup: PokemonDataEggGroupEntity = PokemonDataEggGroupEntity(
                        name = item
                    )
                    myPokeEggGroups.add(pokemonEggGroup)
                    saveEggGroupToDB(pokemonEggGroup)

                }

                //Stats
                //clean stats string by removing "{}" from the string
                val newStats: String = stats!!.drop(1).dropLast(1)
                val newStats2: List<String> = newStats.split(",")

                val myPokeStats: PokemonDataStatEntity = PokemonDataStatEntity(
                    hp = newStats2[0].split(": ")[1].toInt(),
                    speed = newStats2[1].split(": ")[1].toInt(),
                    attack = newStats2[2].split(": ")[1].toInt(),
                    defence = newStats2[3].split(": ")[1].toInt(),
                    specialAttack = newStats2[4].split(": ")[1].toInt(),
                    specialDefence = newStats2[5].split(": ")[1].toInt()
                )

                saveStatToDB(myPokeStats)

                // create pokemon
                val pokemon: PokemonDataEntity = PokemonDataEntity(
//                    id = id?.toInt(),
                    pokeName = name,
                    height = height.toDouble(),
                    weight = weight.toDouble(),
                    genus = genus,
                    description = description,
                    types = myPokeTypes,
                    abilities = myPokeAbilities,
                    eggGroups = myPokeEggGroups,
                    stats = myPokeStats
                )

                saveToDB(pokemon)
            }
        }

    }

    // Repo functions
    fun saveToDB(pokemon: PokemonDataEntity): PokemonDataEntity {
        return pokemonDataRepository.save(pokemon)
    }

    fun saveTypeToDB(pokemonType: PokemonDataTypeEntity): PokemonDataTypeEntity {
        return pokemonDataTypeRepository.save(pokemonType)
    }

    fun saveAbilityToDB(pokemonAbility: PokemonDataAbilityEntity): PokemonDataAbilityEntity {
        return pokemonDataAbilityRepository.save(pokemonAbility)
    }

    fun saveEggGroupToDB(pokemonEggGroup: PokemonDataEggGroupEntity): PokemonDataEggGroupEntity {
        return pokemonDataEggGroupEntityRepository.save(pokemonEggGroup)
    }

    fun saveStatToDB(pokemonStat: PokemonDataStatEntity): PokemonDataStatEntity {
        return pokemonDataStatRepository.save(pokemonStat)
    }



//    fun PokemonDataEntity.getTypes(): MutableList<PokemonDataTypeEntity> {
//        return types
//    }
//
//    fun PokemonDataEntity.setTypes(types: List<PokemonDataTypeEntity>) = types
//
//    fun PokemonDataEntity.getAbilities(): MutableList<PokemonDataAbilityEntity> {
//        return abilities
//    }
//
//    fun PokemonDataEntity.setAbilities(abilities: List<PokemonDataAbilityEntity>) = abilities
//
//    fun PokemonDataEntity.getEggGroups(): MutableList<PokemonDataEggGroupEntity> {
//        return eggGroups
//    }
//    fun PokemonDataEntity.setEggGroups(eggGroups: List<PokemonDataEggGroupEntity>) = eggGroups
//


} // End PokemonSeedDataService


@Entity
@Table(name = "pokemonData1")
class PokemonDataEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val pokeName: String,
    val height: Double,
    val weight: Double,
    val genus: String,
    val description: String,

    @OneToMany(cascade = [CascadeType.PERSIST])
    val types: MutableList<PokemonDataTypeEntity> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.PERSIST])
    val abilities: MutableList<PokemonDataAbilityEntity> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.PERSIST])
    val eggGroups: MutableList<PokemonDataEggGroupEntity> = mutableListOf(),

    @OneToOne
    val stats: PokemonDataStatEntity
)

@Entity
@Table(name = "pokemonDataType1")
class PokemonDataTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String
    )

@Entity
@Table(name = "pokemonDataAbility1")
class PokemonDataAbilityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String
)

@Entity
@Table(name = "pokemonDataEggGroup1")
class PokemonDataEggGroupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String
)

@Entity
@Table(name = "statData1")
class PokemonDataStatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val speed: Int,
    val specialAttack: Int,
    val specialDefence: Int
)

// Stat Model
data class StatTest(
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val speed: Int,

    @JsonProperty("special_attack")
    val specialAttack: Int,

    @JsonProperty("special_defence")
    val specialDefence: Int
)


// Pokemon List Response Model
data class PokemonListResponseTest(
    val id: Int? = null,
    val name: String,
    val types: List<String>
)

// Pokemon Response Model
data class PokemonResponseTest(
    val id: Int? = null,
    val name: String,
    val types: List<String>,
    val height: Double,
    val weight: Double,
    val abilities: List<String>,

    @JsonProperty("egg_groups")
    val eggGroups: List<String>,
    val stats: StatTest,
    val genus: String,
    val description: String
)

// PokemonEntity to Response Model
fun PokemonDataEntity.toResponseTest(): PokemonResponseTest {
    return PokemonResponseTest(
        id = id,
        name = pokeName,
        types = types.map { type -> type.name },
        height = height,
        weight = weight,
        abilities = abilities.map { ability -> ability.name},
        eggGroups = eggGroups.map { eggGroup -> eggGroup.name },
        stats = stats.toModelTest(),
        genus = genus,
        description = description

    )
}

fun PokemonDataEntity.toListResponse(): PokemonListResponseTest {
    return PokemonListResponseTest(
        id = id,
        name = pokeName,
        types = types.map { type -> type.name },
    )
}

// StatEntity to Stat Model
fun PokemonDataStatEntity.toModelTest(): StatTest {
    return StatTest(
        hp = hp,
        attack = attack,
        defence = defence,
        speed = speed,
        specialAttack = specialAttack,
        specialDefence = specialDefence
    )
}

@Repository
interface PokemonDataRepository : JpaRepository<PokemonDataEntity, Int> {}

@Repository
interface PokemonDataTypeRepository : JpaRepository<PokemonDataTypeEntity, Int> {}

@Repository
interface PokemonDataAbilityRepository : JpaRepository<PokemonDataAbilityEntity, Int> {}

@Repository
interface PokemonDataEggGroupRepository : JpaRepository<PokemonDataEggGroupEntity, Int> {}

@Repository
interface PokemonDataStatRepository : JpaRepository<PokemonDataStatEntity, Int> {}


/*
@Entity
@Table(name = "SUPER_HERO")
class SuperHero {
    @Id
    @GeneratedValue
    var id: Int? = null
    var name: String? = null

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "SuperHero_Movies",
        joinColumns = [JoinColumn(name = "superhero_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    private val movies: Set<Movie> = HashSet<Movie>()

    constructor() {}
    constructor(id: Int?, name: String?) {
        this.id = id
        this.name = name
    }

    constructor(name: String?) {
        this.name = name
    }

    fun getMovies(): Set<Movie> {
        return movies
    }

}

@Entity
@Table(name = "MOVIE")
class Movie {
    @Id
    @GeneratedValue
    var id: Int? = null
    var title: String? = null

    @ManyToMany(mappedBy = "movies", cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    private val superHeroes: MutableSet<SuperHero> = HashSet()

    constructor() {}
    constructor(id: Int?, title: String?) {
        this.id = id
        this.title = title
    }

    constructor(title: String?) {
        this.title = title
    }

    fun getSuperHeroes(): Set<SuperHero> {
        return superHeroes
    }

    fun addSuperHero(superHero: SuperHero) {
        superHeroes.add(superHero)
        superHero.getMovies().add(this)
    }
}


//    @JoinTable(
//        name = "pokemonData1_pokemonDataTypes1",
//        joinColumns = [
//            JoinColumn(name = "pokemon_Data1_id")
//        ],
//        inverseJoinColumns = [
//            JoinColumn(name = "pokemon_data_types1_id")
//        ]
//    )

*/