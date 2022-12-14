package dev.patbeagan.days

/**
 * [Day 11](https://adventofcode.com/2022/day/11)
 */
class Day11 : AdventDay<Int> {
    override fun part1(input: String) = 0

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n\n")
        .map { Monkey.parse(it) }

    @JvmInline
    value class Item(val s: String)

    enum class Operation

    class MonkeyTest(
        val onTrue: () -> Unit,
        val onFalse: () -> Unit,
    )

    class Monkey(
        val id: Int,
        val startingItems: List<Item>,
        val operation: Operation,
        val test: MonkeyTest,
    ) {
        companion object {
            fun parse(input: String) {
//                return Monkey(
//                    regexMonkeyId.find(input).value,
//                    regexStartingItems.find()
//                )
            }

            val regexMonkeyId = Regex("\\w+Monkey (\\d*):")
            val regexStartingItems = Regex("\\w+Starting items: (\\d* )*")
            val regexOperation = Regex("\\w+Operation: (.*)")
            val regexTest = Regex("\\w+Test: (.*)")
            val regexOnTrue = Regex("\\w+If true: (.*)")
            val regexOnFalse = Regex("\\w+If false: (.*)")
        }
    }
}