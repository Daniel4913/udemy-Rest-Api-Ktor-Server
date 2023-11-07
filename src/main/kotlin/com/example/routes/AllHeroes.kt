package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.lang.NumberFormatException

fun Route.getAllHeroes() {
    val heroRepository: HeroRepository by inject()

    get("/burto/heroes") {
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val allPages = 5

        try {
            require(page in 1..allPages) // jezeli nie będzie 1-5 to zwróci IllegalArgumentException

            val apiResponse = heroRepository.getAllHeroes(page = page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "To access pages, only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Page '$page' with heroes not Found. Last page $allPages"
                ),
//                status = HttpStatusCode.BadRequest
                status = HttpStatusCode.NotFound
            )
        }
    }
}