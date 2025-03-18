package com.nameisjayant.androidpractise.ui.kotlin


class Box(val type: Any)
interface Box2<T> {
    fun consume(data: T)
}

fun main() {
    val intBox = Box(10)
    val retreiveValue = intBox.type as Int
    println(retreiveValue * 10)
    val numbers = listOf(1, 2, 3)
    printList(numbers)

    val strings: List<String> = listOf("Hello", "World")
    val anyList: List<Any> = strings

    val mutableStrings: MutableList<String> = mutableListOf("Hello", "World")
//    val mutableAnyList: MutableList<Any> = mutableStrings

    val fruitProducer: Producer<Any> = FruitProducer()
    val fruits: Any = fruitProducer.produce()

}
// generics function

fun <T> printValue(value: T) {
    println(value)
}

// multi-params generics
fun <T, U> printTwoValue(first: T, second: U): String {
    return "first : $first , second  : $second"
}

// generics constraints

fun <T : Number> printNumber(data: T) {

}

fun printList(list: List<Any>) {
    for (item in list) {
        println(item)
    }
}

interface Producer<out T> {
    fun produce(): T
}

class FruitProducer : Producer<MutableList<String>> {
    override fun produce(): MutableList<String> = mutableListOf("Hello", "World")
}