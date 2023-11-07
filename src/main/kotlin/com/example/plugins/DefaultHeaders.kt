package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.netty.handler.codec.DefaultHeaders
import java.time.Duration

fun Application.configureDefaultHeader() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
//        header("X-Engine", "Ktor") // will send this header with each response
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=$oneYearInSeconds, immutable"
        )
    }

}