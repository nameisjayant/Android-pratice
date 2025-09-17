package com.nameisjayant.androidpractise.ui.kotlin


// Defines two overloads
fun transformFun(block: () -> Int) {}
fun transformFun(block: suspend () -> Int) {}

fun test() {
    // Fails with overload resolution ambiguity
    transformFun(block = { 32 } as () -> Int)

    // Uses an explicit cast, but the compiler incorrectly reports
    // a "No cast needed" warning
    transformFun(block = suspend { 42 })
}

fun example(): Int = 23

enum class Problem {
    CONNECTION, AUTHENTICATION, DATABASE, UNKNOWN
}

fun message(problem: Problem): String = when (problem) {
    Problem.CONNECTION -> "connection"
    Problem.AUTHENTICATION -> "authentication"
    Problem.DATABASE -> "database"
    Problem.UNKNOWN -> "unknown"
}