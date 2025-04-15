package com.nameisjayant.androidpractise.ui.designpattern


abstract class Animal {
    abstract fun speak()
    abstract fun name(): String
}

class Dog : Animal() {
    override fun speak() {
        println("WOOF")
    }

    override fun name(): String = "DOG"

}

class Cat : Animal() {
    override fun speak() {
        println("MEOW")
    }

    override fun name(): String = "CAT"

}

class Lion : Animal() {
    override fun speak() {
        println("Roar")
    }

    override fun name(): String = "Lion"

}

fun main() {
    val animals = listOf<Animal>(Dog(), Cat(), Lion())
    animals.forEach {
       "${print("${it.name()} - ")}  ${it.speak()}"
    }
}