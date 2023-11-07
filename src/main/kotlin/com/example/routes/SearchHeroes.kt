package com.example.routes

import com.example.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val SEARCH_HERO_BY_NAME = "name"
fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject()

    get(path = "/burto/heroes/search") {
        val name = call.request.queryParameters[SEARCH_HERO_BY_NAME]

        val apiResponse = heroRepository.searchHeroes(name = name)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}