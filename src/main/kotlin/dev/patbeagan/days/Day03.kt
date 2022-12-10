package dev.patbeagan.days

import dev.patbeagan.AdventDay

/**
 * [Day 3](https://adventofcode.com/2022/day/3)
 */
class Day03 : AdventDay<String, Int> {
    override fun part1(input: String) = parseInput(input)
        .sumOf { rucksack ->
            rucksack.getMatchingItems().sumOf {
                it.toPriority()!!
            }
        }

    override fun part2(input: String) = parseInput(input)
        .chunked(3)
        .sumOf { ElfSquad(it[0], it[1], it[2]).findBadge().toPriority()!! }

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map {
            Rucksack(
                it.substring(0, it.length / 2),
                it.substring(it.length / 2, it.length)
            )
        }

    private fun Char.toPriority() = when (this) {
        in CharRange('a', 'z') -> this.code - 96
        in CharRange('A', 'Z') -> this.code - 38
        else -> null
    }

    data class Rucksack(
        val compartment1: String,
        val compartment2: String,
    ) {
        fun getMatchingItems() = compartment1.toSet() intersect compartment2.toSet()
        fun contents() = compartment1 + compartment2
    }

    data class ElfSquad(
        val rucksack1: Rucksack,
        val rucksack2: Rucksack,
        val rucksack3: Rucksack,
    ) {
        private val contents = setOf(
            rucksack1,
            rucksack2,
            rucksack3
        )

        fun findBadge() = contents
            .fold(rucksack1.contents().toSet()) { acc, each ->
                acc intersect each.contents().toSet()
            }.first()
    }
}

