package dev.myhappyplace.headlineduelkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform