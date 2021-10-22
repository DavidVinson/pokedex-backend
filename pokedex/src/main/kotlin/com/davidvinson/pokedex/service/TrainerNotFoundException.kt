package com.davidvinson.pokedex.service

import org.springframework.http.HttpStatus

class TrainerNotFoundException(val statusCode: HttpStatus, val reason: String): Exception()