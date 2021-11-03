package com.davidvinson.pokedex.model

import com.davidvinson.pokedex.repository.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Service

@Service
class PokemonSeedDataService(
    val pokemonRepository: PokemonRepository,
    val typeRepository: TypeRepository,
    val abilityRepository: AbilityRepository,
    val eggGroupEntityRepository: EggGroupRepository,
    val dataStatRepository: StatRepository
    ) {

    private val filePath = "src/main/resources/rawData5-copy.csv"

    fun databaseInitializer() {

        csvReader().open(filePath) {
            readAllWithHeaderAsSequence().forEach { row ->
                val id: String = row["id"].toString() // row.get("id").toString()
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
                val myPokeTypes: MutableList<TypeEntity> = mutableListOf()
                newTypesSet.forEach { item ->
                    val pokemonType: TypeEntity = TypeEntity(
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
                val myPokeAbilities: MutableList<AbilityEntity> = mutableListOf()
                newAbilitiesSet.forEach { item ->
                    val pokemonAbility: AbilityEntity = AbilityEntity(
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
                val myPokeEggGroups: MutableList<EggGroupEntity> = mutableListOf()
                newEggGroupsSet.forEach { item ->
                    val pokemonEggGroup: EggGroupEntity = EggGroupEntity(
                        name = item
                    )
                    myPokeEggGroups.add(pokemonEggGroup)
                    saveEggGroupToDB(pokemonEggGroup)

                }

                //Stats
                //clean stats string by removing "{}" from the string
                val newStats: String = stats.drop(1).dropLast(1)
                val newStats2: List<String> = newStats.split(",")

                val myPokeStats: StatEntity = StatEntity(
                    hp = newStats2[0].split(": ")[1].toInt(),
                    speed = newStats2[1].split(": ")[1].toInt(),
                    attack = newStats2[2].split(": ")[1].toInt(),
                    defence = newStats2[3].split(": ")[1].toInt(),
                    specialAttack = newStats2[4].split(": ")[1].toInt(),
                    specialDefence = newStats2[5].split(": ")[1].toInt()
                )

                saveStatToDB(myPokeStats)

                // create pokemon
                val pokemon: PokemonEntity = PokemonEntity(
//                    id = id?.toInt(),
                    name = name,
                    image = null,
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
    fun saveToDB(pokemon: PokemonEntity): PokemonEntity {
        return pokemonRepository.save(pokemon)
    }

    fun saveTypeToDB(pokemonType: TypeEntity): TypeEntity {
        return typeRepository.save(pokemonType)
    }

    fun saveAbilityToDB(pokemonAbility: AbilityEntity): AbilityEntity {
        return abilityRepository.save(pokemonAbility)
    }

    fun saveEggGroupToDB(pokemonEggGroup: EggGroupEntity): EggGroupEntity {
        return eggGroupEntityRepository.save(pokemonEggGroup)
    }

    fun saveStatToDB(pokemonStat: StatEntity): StatEntity {
        return dataStatRepository.save(pokemonStat)
    }
} // End PokemonSeedDataService