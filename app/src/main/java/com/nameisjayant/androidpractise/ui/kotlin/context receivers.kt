package com.nameisjayant.androidpractise.ui.kotlin


data class TestUser(
    val name: String
)

class Loggers {
    fun log(message: String) {
        print(message)
    }
}

context(TestUser, Loggers)
fun printUserInfo() {
    log("User: $name")
}

fun printUserInfo(user: User, logger: Loggers) {
    logger.log("User: ${user.name}")
}