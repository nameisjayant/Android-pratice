package com.nameisjayant.androidpractise.ui.kotlin


val lambdaFunction: () -> Unit = {
    print("Hello World")
}

fun normalFunction() {
    println("Hello world")
}

val sums = { a: Int, b: Int -> a + b }


//val add: (x: Int, y: Int) -> Int = { a, b -> a + b }
//val multiply: (x: Int, y: Int) -> Int = { a, b -> a * b }

fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b)

fun add(x: Int, y: Int) = x + y
fun multiply(x: Int, y: Int) = x * y


fun getDiscount(type: String): (Double) -> Double {
    return when (type) {
        "Student" -> { price -> price * 0.8 }
        "Senior" -> { price -> price * 0.7 }
        else -> { price -> price }
    }
}

fun main() {
    val discount = getDiscount("Student")
    println(discount(128.0))

    val numbers = listOf(1, 2, 3, 4, 5, 6)

    val evenNumbers = numbers.filter(::filterData)
    val squaredNumbers = numbers.map { it * it }

}

fun filterData(data: Int): Boolean = data % 2 == 0



