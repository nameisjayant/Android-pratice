package com.nameisjayant.androidpractise.ui.kotlin


class Discount(val price: Int) {

    infix fun discount(discount: Double): Double {
        return price * discount
    }
}


infix fun String.joinString(otherString: String): String = "$this $otherString"

fun main() {
    val and = 10 and 16
    val or = 10 or 13
    val xor = 10 xor 23
}