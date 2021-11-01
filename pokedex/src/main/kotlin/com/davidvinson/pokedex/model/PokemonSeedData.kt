package com.davidvinson.pokedex.model

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*


@Service
class PokemonSeedDataService(
    val pokemonDataRepository: PokemonDataRepository,
    val pokemonDataTypeRepository: PokemonDataTypeRepository,
    val pokemonDataAbilityRepository: PokemonDataAbilityRepository
    ) {

    private val filePath = "src/main/resources/rawData-copy3.csv"

    fun databaseInitializer() {


        csvReader().open(filePath) {
            readAllWithHeaderAsSequence().forEach { row ->
                val id: String? = row["id"] // row.get("id")
                val name: String? = row["name"]
                val height: String? = row["height"]
                val weight: String? = row["weight"]
                val genus: String? = row["genus"]
                val description: String? = row["description"]
                val types: String? = row["types"]
                val abilities: String? = row["abilities"]

                //Types
                //clean types string by removing "[]" from the string
                val newTypes: String = types!!.drop(1).dropLast(1)
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
                val newAbilities: String = abilities!!.drop(1).dropLast(1)
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

                // create pokemon
                val pokemon: PokemonDataEntity = PokemonDataEntity(
//                    id = id?.toInt(),
                    pokeName = name,
                    height = height?.toDouble(),
                    weight = weight?.toDouble(),
                    genus = genus,
                    description = description,
                    types = myPokeTypes,
                    abilities = myPokeAbilities
                )

                saveToDB(pokemon)
            }
        }

    }

    // Repo function
    fun saveToDB(pokemon: PokemonDataEntity): PokemonDataEntity {
        return pokemonDataRepository.save(pokemon)
    }

    // Repo function
    fun saveTypeToDB(pokemonType: PokemonDataTypeEntity): PokemonDataTypeEntity {
        return pokemonDataTypeRepository.save(pokemonType)
    }

    fun saveAbilityToDB(pokemonAbility: PokemonDataAbilityEntity): PokemonDataAbilityEntity {
        return pokemonDataAbilityRepository.save(pokemonAbility)
    }

    // Function Extension
    fun PokemonDataEntity.getTypes(): MutableList<PokemonDataTypeEntity> {
        return types
    }

    fun PokemonDataEntity.setTypes(types: List<PokemonDataTypeEntity>) = types

    fun PokemonDataEntity.getAbilities(): MutableList<PokemonDataAbilityEntity> {
        return abilities
    }

    fun PokemonDataEntity.setAbilities(abilities: List<PokemonDataAbilityEntity>) = abilities


} // End PokemonSeedDataService




@Entity
@Table(name = "pokemonData1")
class PokemonDataEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val pokeName: String?,
    val height: Double?,
    val weight: Double?,
    val genus: String?,
    val description: String?,

    @OneToMany(cascade = [CascadeType.PERSIST])
    val types: MutableList<PokemonDataTypeEntity> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.PERSIST])
    val abilities: MutableList<PokemonDataAbilityEntity> = mutableListOf(),
)

@Entity
@Table(name = "pokemonDataType1")
class PokemonDataTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String?,
    )

@Entity
@Table(name = "pokemonDataAbility1")
class PokemonDataAbilityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String?
)

//@Entity
//@Table(name = "eggGroup1")
//class PokemonEggGroupEntity(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int? = null,
//    val name: String
//)


// Pokemon List Response Model
data class PokemonListResponseTest(
    val id: Int? = null,
    val name: String?,
    val types: List<String?>,
    val abilities: List<String?>
)

fun PokemonDataEntity.toListResponse(): PokemonListResponseTest {
    return PokemonListResponseTest(
        id = id,
        name = pokeName,
        types = types.map { type -> type.name },
        abilities = abilities.map { ability -> ability.name}
    )
}

@Repository
interface PokemonDataRepository : JpaRepository<PokemonDataEntity, String> {}

@Repository
interface PokemonDataTypeRepository : JpaRepository<PokemonDataTypeEntity, String> {}

@Repository
interface PokemonDataAbilityRepository : JpaRepository<PokemonDataAbilityEntity, String> {}


//@Repository
//interface PokemonStatRepository : JpaRepository<PokemonStatEntity, String> {}


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