package com.davidvinson.pokedex.model

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.*

@Service
class PokemonSeedDataService(val pokemonDataRepository: PokemonDataRepository) {

    private val filePath = "src/main/resources/rawData-copy.csv"

    fun databaseInitializer() {
        csvReader().open(filePath) {
            readAllWithHeaderAsSequence().forEach { item -> println(item["name"]) }
        }

    }
}

data class SeedData(
    val name: String?,
//    val height: String,
//    val weight: String,
//    val genus: String,
//    val description: String
    )

//fun saveToDB(data: String?): PokemonDataEntity {
//
//}

@Entity
@Table(name = "pokemonData")
class PokemonDataEntity(
    @Id
    val id: String?,
    val pokeName: String,
//    val height: String?,
//    val weight: String?,
//    val genus: String?,
//    val description: String?

)

@Repository
interface PokemonDataRepository : JpaRepository<PokemonDataEntity, String> {}


//@Service
//class PokemonDataService(val pokemonDataRepository: PokemonDataRepository) {
//    fun createPokemonData(pokemonData: PokemonDataEntity): PokemonDataEntity = pokemonDataRepository.save(pokemonData)
//}

//pokemonDataRepository.save(
//PokemonDataEntity(
//id = item["id"],
//name = item["name"],
//height = item["height"],
//weight = item["weight"],
//genus = item["genus"],
//description = item["description"]
//)
//)


