package com.nameisjayant.androidpractise.ui.designpattern


interface IceCream {
    fun flavours()
}

class ChocolateIceCream : IceCream {
    override fun flavours() {
        println("Chocolate Ice Cream")
    }
}

class StrawberryIceCream : IceCream {
    override fun flavours() {
        println("Strawberry Ice cream")
    }
}

class VanillaIceCream : IceCream {
    override fun flavours() {
        println("Vanilla Ice cream")
    }
}

enum class IceCreamType {
    CHOCOLATE,
    STRAWBERRY,
    VANILLA
}

object IceCreamFactory {
    fun create(type: IceCreamType): IceCream {
        return when (type) {
            IceCreamType.CHOCOLATE -> ChocolateIceCream()
            IceCreamType.STRAWBERRY -> StrawberryIceCream()
            IceCreamType.VANILLA -> VanillaIceCream()
        }
    }
}

fun main() {
    val chocolateFlavour = IceCreamFactory.create(IceCreamType.CHOCOLATE)
    chocolateFlavour.flavours() // output ---> Chocolate Ice Cream
}
