package com.nameisjayant.androidpractise.ui.designpattern

interface SquareCharger {
    fun chargeSquareToy()
}

class RoundCharger {
    fun chargeRoundToy() {
        println("Charging toy with round charger")
    }
}

class RoundToSquareAdapter(private val roundCharger: RoundCharger) : SquareCharger {
    override fun chargeSquareToy() {
        // Use the round charger inside the square interface
        roundCharger.chargeRoundToy()
    }
}

fun main() {
    val roundCharger = RoundCharger()
    val adapter = RoundToSquareAdapter(roundCharger)

    // Our code only knows about SquareCharger
    adapter.chargeSquareToy()
}
