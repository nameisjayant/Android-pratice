package com.nameisjayant.androidpractise.ui.designpattern


data class ToyRobot(
    val head: String?,
    val body: String?,
    val legs: String?,
    val color: String?
)

class ToyBuilder {
    var head: String? = null
    var body: String? = null
    var legs: String? = null
    var color: String? = null

    fun setHead(head: String) = apply { this.head = head }
    fun setBody(body: String) = apply { this.body = body }
    fun setLegs(legs: String) = apply { this.legs = legs }
    fun setColor(color: String) = apply { this.color = color }

    fun build(): ToyRobot = ToyRobot(head, body, legs, color)
}

fun robot(block: ToyBuilder.() -> Unit): ToyRobot {
    return ToyBuilder().apply(block).build()
}

fun main() {
    val myRobot = ToyBuilder()
        .setHead("Laser Head")
        .setBody("Steel Body")
        .setColor("Red")
        .build()
    println("You built a robot with: ${myRobot.head}, ${myRobot.body}, ${myRobot.legs}, and color ${myRobot.color}")

    val robot = robot {
        setHead("Laser Head")
        setBody("Steel Body")
        setColor("Red")
    }
    println("You built a robot with: ${robot.head}, ${robot.body}, ${robot.legs}, and color ${robot.color}")

}