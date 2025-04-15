package com.nameisjayant.androidpractise.ui.kotlin


data class UserId(
    val id: Int
)

@JvmInline
value class Print(val name: String)

@JvmInline
value class Person(val fullName: String) {

    init {
        require(fullName.isNotEmpty()) {
            println("Can't be empty")
        }
    }

    constructor(firstName: String, lastName: String) : this("$firstName $lastName") {

    }

    val length get() = fullName.length

}

interface Printable {
    fun print()
}

@JvmInline
value class PrintManager(val name: String) : Printable {

    override fun print() {
        println("$name is ready for printing...")
    }
}

fun main() {
    val print = PrintManager("Kotlin")
    print.print()
}

@JvmInline
value class UserData<T>(val id: T)

fun getUserId(id: UserData<String>) {

}