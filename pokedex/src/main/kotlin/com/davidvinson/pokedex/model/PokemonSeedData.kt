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
            readAllWithHeaderAsSequence().forEach { row ->
                //Do something
                val id: String? = row["id"]
                val name: String? = row["name"]
                val height: String? = row["height"]
                val weight: String? = row["weight"]
                val genus: String? = row["genus"]
                val description: String? = row["description"]

                val pokemon: PokemonDataEntity = PokemonDataEntity(
                    id = id?.toInt(),
                    pokeName = name,
                    height = height?.toDouble(),
                    weight = weight?.toDouble(),
                    genus = genus,
                    description = description
                )

                saveToDB(pokemon)

            }
        }
    }
    fun saveToDB(pokemon: PokemonDataEntity): PokemonDataEntity = pokemonDataRepository.save(pokemon)

}


@Entity
@Table(name = "pokemonData")
class PokemonDataEntity(
    @Id
    val id: Int?,
    val pokeName: String?,
    val height: Double?,
    val weight: Double?,
    val genus: String?,
    val description: String?

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


