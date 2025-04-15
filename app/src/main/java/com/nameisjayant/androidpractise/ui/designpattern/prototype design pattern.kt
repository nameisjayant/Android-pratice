package com.nameisjayant.androidpractise.ui.designpattern


data class Robot(
    val name: String,
    val color: String,
    val canTalk: Boolean,
    val canDance: Boolean
) {
    fun clone(): Robot = copy()
}

fun main() {
    val originalRobot = Robot(
        "RoboX",
        "RED",
        true,
        true
    )
    println("Original Robot : $originalRobot")

    val cloneRobot = originalRobot.clone()
    println("Clone Robot : $cloneRobot")

    val modifiedRobot = originalRobot.clone().copy(color = "Blue")
    println("Modified Robot : $modifiedRobot")

    val robot1 = SmartRobot("Alpha", mutableListOf("Talk", "Dance"))
    val robot2 = robot1.clone() as SmartRobot
    robot2.skills.add("jump")
    robot2.name = "Beta"

    println("Robot 1 : $robot1")
    println("Robot 2 : $robot2")
}

interface CloneableRobot {
    fun clone(): CloneableRobot
}

class SmartRobot(
    var name: String,
    val skills: MutableList<String>
) : CloneableRobot {
    override fun clone(): CloneableRobot {
        return SmartRobot(name, skills.toMutableList())
    }

    override fun toString(): String {
        return "SmartRobot(name='$name', skills=$skills)"
    }
}