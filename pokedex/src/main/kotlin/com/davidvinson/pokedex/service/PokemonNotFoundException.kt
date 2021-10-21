package com.davidvinson.pokedex.service

import org.springframework.http.HttpStatus

class PokemonNotFoundException(val statusCode: HttpStatus, val reason: String): Exception()