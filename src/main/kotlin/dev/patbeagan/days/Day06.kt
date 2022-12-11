package dev.patbeagan.days

import dev.patbeagan.AdventDay

/**
 * [Day 6](https://adventofcode.com/2022/day/6)
 */
class Day06 : AdventDay<Int> {
    override fun part1(input: String): Int {
        val buffer = mutableListOf<Char>()
        input.forEachIndexed { index, each ->
            buffer.add(each)
            if (buffer.size == 4) {
                if (buffer.distinct().size == buffer.size) {
                    return index + 1
                }
                buffer.removeAt(0)
            }
        }
        return -1
    }

    override fun part2(input: String): Int {
        val buffer = mutableListOf<Char>()
        input.forEachIndexed { index, each ->
            buffer.add(each)
            if (buffer.size == 14) {
                if (buffer.distinct().size == buffer.size) {
                    return index + 1
                }
                buffer.removeAt(0)
            }
        }
        return -1
    }

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
}

