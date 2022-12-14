package dev.patbeagan.days

/**
 * [Day 1](https://adventofcode.com/2022/day/1)
 */
class Day01 : AdventDay<Int> {
    /**
     * Prints out the calorie value
     * of the elf that is holding the most calories.
     */
    override fun part1(input: String): Int =
        parseForElves(input)
            .maxBy { it.totalCalories }
            .totalCalories

    /**
     * Prints out the calorie value
     * of the top 3 elves that are holding the most calories.
     */
    override fun part2(input: String): Int =
        parseForElves(input)
            .sortedByDescending { it.totalCalories }
            .take(3)
            .sumOf { it.totalCalories }

    /**
     * Converts the incoming text into a list of elves
     */
    fun parseForElves(input: String): List<Elf> = input
        .trim()
        .split("\n\n")
        .map { s -> Elf(s.split("\n").map { it.toInt() }) }

    data class Elf(
        val calorieItems: List<Int>,
    ) {
        val totalCalories get() = calorieItems.sum()
    }
}

