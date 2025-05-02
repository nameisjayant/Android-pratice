package com.nameisjayant.androidpractise.ui.designpattern


interface IceCreamShop{
    fun make(): String
}

class Vanilla : IceCreamShop{
    override fun make(): String  = "Vanilla Ice cream"
}

class ChocolateSyrup(val iceCreamShop: IceCreamShop) : IceCreamShop {
    override fun make(): String  = iceCreamShop.make() + " + chocolate syrup"
}

class Sprinkles(val iceCreamShop: IceCreamShop) : IceCreamShop {
    override fun make(): String  = iceCreamShop.make() + " + Sprinkles"
}

fun main() {
    val vanilla = Vanilla()
    val chocolateSyrup = ChocolateSyrup(vanilla)
    val sprinkles = Sprinkles(chocolateSyrup)

    println(sprinkles.make())
}