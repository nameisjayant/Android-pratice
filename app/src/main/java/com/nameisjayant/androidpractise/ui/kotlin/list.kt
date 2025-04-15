package com.nameisjayant.androidpractise.ui.kotlin


fun main() {
    val data = listOf("2", "4", null, "6")
    val parsedNumbers = data.mapNotNull { it?.toIntOrNull() }
    println(parsedNumbers) // Output: [2, 4, 6]

    val words = listOf("apple", "banana", "avocado", "blueberry")
    val grouped = words.groupBy { it.first() }
    println(grouped)
// Output: {a=[apple, avocado], b=[banana, blueberry]}

    val users = listOf(
        User1(1, "Alice"), User1(2, "Bob"), User1(3, "Alice")
    )
    val uniqueUsers = users.distinctBy { it.name }
    println(uniqueUsers) // Output: [User(1, Alice), User(2, Bob)]

    val nestedList = listOf(listOf(1, 2, 3), listOf(4, 5))
    val flatList = nestedList.flatten()
    println(flatList) // Output: [1, 2, 3, 4, 5]

    val names = listOf("Alice", "Bob")
    val ages = listOf(25, 30)
    val paired = names.zip(ages)
    println(paired) // Output: [(Alice, 25), (Bob, 30)]

    val numbers = listOf(1, 2, 3, 4, 5)
    val (evens, odds) = numbers.partition { it % 2 == 0 }
    println(evens) // Output: [2, 4]
    println(odds)  // Output: [1, 3, 5]

    val number = (1..10).toList()
    val chunks = number.chunked(3)
    println(chunks) // Output: [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
    val number1 = listOf(1, 2, 3, 4)
    val sum = number1.fold(0) { acc, num -> acc + num }
    println(sum) // Output: 10
}

data class User1(
    val id: Int,
    val name: String
)
