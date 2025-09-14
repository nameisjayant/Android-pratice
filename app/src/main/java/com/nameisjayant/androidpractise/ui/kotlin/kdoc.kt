package com.nameisjayant.androidpractise.ui.kotlin


/**
 * Calculates the area of a rectangle.
 *
 * @param width The width of the rectangle.
 * @param height The height of the rectangle.
 * @return The area calculated as width * height.
 * @throws
 * @exception
 */
fun calculateArea(width: Int, height: Int): Int {
    return width * height
}

/**
 * Represents a user in the system.
 *
 * @property id Unique identifier for the user.
 * @property name Full name of the user.
 * @constructor Creates a user with an ID and name.
 */
//data class User(val id: Int, val name: String)