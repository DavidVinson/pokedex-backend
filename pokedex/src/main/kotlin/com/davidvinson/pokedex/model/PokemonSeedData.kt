package com.davidvinson.pokedex.model

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*

@Service
class PokemonSeedDataService(
    val pokemonDataRepository: PokemonDataRepository,
    val pokemonDataTypeRepository: PokemonDataTypeRepository
    ) {

    private val filePath = "src/main/resources/rawData-copy2.csv"

    fun databaseInitializer() {

        val newTypesSet: MutableSet<String> = mutableSetOf()

        csvReader().open(filePath) {
            readAllWithHeaderAsSequence().forEach { row ->
                val id: String? = row["id"] // row.get("id")
                val name: String? = row["name"]
                val height: String? = row["height"]
                val weight: String? = row["weight"]
                val genus: String? = row["genus"]
                val description: String? = row["description"]
                val types: String? = row["types"]

                val pokemon: PokemonDataEntity = PokemonDataEntity(
//                    id = id?.toInt(),
                    pokeName = name,
                    height = height?.toDouble(),
                    weight = weight?.toDouble(),
                    genus = genus,
                    description = description,
                    types =
                )

                //clean types string by removing "[]" from the string
                val newTypes: String = types!!.drop(1).dropLast(1)
                //clean types by creating a list of types
                val newTypes2: List<String> = newTypes.split(",")
                //create a unique set of pokemon types
                newTypes2.forEach { it -> newTypesSet.add(it.trim())}

                saveToDB(pokemon)
            }


        }
        //loop through newTypesSet and create entity to be saved to db
        newTypesSet.forEach { item ->
            val pokemonType: PokemonDataTypeEntity = PokemonDataTypeEntity(
                name = item
            )
            saveTypeToDB(pokemonType)
        }

//        println(newTypesSet.size)

    }

    fun saveToDB(pokemon: PokemonDataEntity): PokemonDataEntity {
        return pokemonDataRepository.save(pokemon)
    }

    fun saveTypeToDB(pokemonType: PokemonDataTypeEntity): PokemonDataTypeEntity {
        return pokemonDataTypeRepository.save(pokemonType)
    }

    } // End PokemonSeedDataService




@Entity
@Table(name = "pokemonData")
class PokemonDataEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val pokeName: String?,
    val height: Double?,
    val weight: Double?,
    val genus: String?,
    val description: String?,

    @ManyToMany
    val types: List<PokemonDataTypeEntity>

)

@Entity
@Table(name = "pokemonDataType")
class PokemonDataTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String?

)

@Repository
interface PokemonDataRepository : JpaRepository<PokemonDataEntity, String> {}

@Repository
interface PokemonDataTypeRepository : JpaRepository<PokemonDataTypeEntity, String> {}





