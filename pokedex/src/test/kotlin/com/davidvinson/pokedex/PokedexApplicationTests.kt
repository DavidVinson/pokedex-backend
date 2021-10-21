package com.davidvinson.pokedex

import com.davidvinson.pokedex.model.Pokemon
import com.davidvinson.pokedex.service.PokemonService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.AssertionErrors.assertEquals
import sun.jvm.hotspot.utilities.Assert


//@SpringBootTest
//class PokemonServiceUnitTest(val pokemonService: PokemonService) {
//	@Test
//	fun whenApplicationStarts_thenHibernateCreatesInitialRecords() {
//		val pokemonList: List<Pokemon> = pokemonService.getAllPokemon()
//		Assert.assertEquals(pokemonList.size, 3)
//	}
//}
