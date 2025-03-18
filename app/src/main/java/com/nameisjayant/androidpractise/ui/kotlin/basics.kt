package com.nameisjayant.androidpractise.ui.kotlin

fun main() {

    val age = 16
    val validAge = age.takeIf { it > 18 } ?: "not valid"
    println(validAge)

    val notValid = age.takeUnless { it >= 18 } ?: "Not Valid"
    println(notValid)

    val point1 = Points(1, 2)
    val point2 = Points(1, 2)
    println(point1 + point2)

    runCatching {
        "abc".toInt() // failed
    }.getOrElse { -1 }

    measure {
        println("Called...")
    }

    val log = Logger()
    log("Kotlin performance tips")
    log(1)

    val printerManager = PrinterManager(ConsolePrinter())
    printerManager.printSomething()

}

data class Points(val x: Int, val y: Int) {
    operator fun plus(other: Points) = Points(x + other.x, y + other.y)
    operator fun minus(other: Points) = Points(x - other.x, y - other.y)
    operator fun div(other: Points) = Points(x / other.x, y / other.y)
    operator fun times(other: Points) = Points(x * other.x, y * other.y)
}

inline fun measure(call: () -> Unit) {
    call()
}

class Logger {
    operator fun invoke(message: String) {
        println(message)
    }

    operator fun invoke(number: Int) {
        println("Kotlin Performance Tip $number")
    }
}

inline fun <reified T> printType() {
    println(T::class.simpleName)
}

interface Printer {
    fun printSomething()
}

class ConsolePrinter : Printer {

    override fun printSomething() {
        println("console printing...")
    }
}

class PrinterManager(private val printer: Printer) : Printer {

    override fun printSomething() {
        printer.printSomething()
    }
}

class PrinterManger2(private val printer: Printer) : Printer by printer // delegate


fun processUser(name: String?, age: Int?) {
    when {
        name == null -> {
            println("Invalid name ❌")
            return
        }
        age == null || age < 18 -> {
            println("Invalid age ❌")
            return
        }
    }

    println("Processing user $name, age $age ✅")
}
