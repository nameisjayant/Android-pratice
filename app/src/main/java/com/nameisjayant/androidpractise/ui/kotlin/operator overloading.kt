package com.nameisjayant.androidpractise.ui.kotlin


data class Point(val x: Int, val y: Int) {

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    operator fun unaryPlus(): Point {
        return Point(+x, +y)
    }

}


data class Score(var score: Int) {

    operator fun inc(): Score {
        return Score(score++)
    }

    operator fun dec(): Score {
        return Score(score--)
    }
}

data class ScoreList(val list: List<Int>) {

//    operator  fun get(index: Int) : Int {
//        return list[index]
//    }

}


data class Light(val isOn: Boolean) {

    operator fun not(): Light {
        return Light(!isOn)
    }
}


class Printers {

    operator fun invoke() {
        println("Printing...")
    }

    operator fun invoke(value: String) {
        println(value)
    }
}


data class Pointer(var x: Int) {

    operator fun plusAssign(other: Pointer) {
        this.x += other.x
    }

    operator fun minusAssign(other: Pointer) {
        this.x -= other.x
    }

    operator fun timesAssign(other: Pointer) {
        this.x *= other.x
    }

    operator fun divAssign(other: Pointer) {
        this.x /= other.x
    }

    operator fun remAssign(other: Pointer) {
        this.x %= other.x
    }
}


data class Grade(val value: Char) {
    operator fun rangeTo(other: Grade): CharRange {
        return this.value..other.value
    }
}


data class NumberList(val list: List<Int>) {
    operator fun contains(number: Int): Boolean {
        return number in list
    }
}

data class ToyBox(val number: Int) {

//    operator fun plus(other: ToyBox): Int {
//        return ToyBox(this.number + other.number).number
//    }
}

fun main() {

    val toyBox1 = ToyBox(5)
    val toyBox2 = ToyBox(3)

    println("Total toys is : ${toyBox1.number + toyBox2.number}")  // output ---> 8
}


