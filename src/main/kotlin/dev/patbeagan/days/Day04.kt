package dev.patbeagan.days

import dev.patbeagan.AdventDay

/**
 * [Day 4](https://adventofcode.com/2022/day/4)
 */
class Day04 : AdventDay<String, Int> {
    override fun part1(input: String) = parseInput(input)
        .count { it.first.fullyContains(it.second) || it.second.fullyContains(it.first) }

    override fun part2(input: String) = parseInput(input)
        .count { !it.first.fullyDistinct(it.second) }

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { line ->
            line
                .split(",")
                .let { CampSections.from(it[0]) to CampSections.from(it[1]) }
        }

    @JvmInline
    value class CampSections(private val range: IntRange) {
        fun fullyContains(other: CampSections) = other.range.all { range.contains(it) }
        fun fullyDistinct(other: CampSections) = other.range.none { range.contains(it) }

        companion object {
            fun from(input: String): CampSections = input
                .split("-")
                .let { IntRange(it[0].toInt(), it[1].toInt()) }
                .let { CampSections(it) }
        }
    }
}