package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()

//        static("/images") { //deprecated
//            resources("images")
//        }
        staticResources(remotePath = "/images", basePackage = "images")
    }
}