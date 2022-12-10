package dev.patbeagan.days

import dev.patbeagan.AdventDay
import java.util.Stack

/**
 * [Day 5](https://adventofcode.com/2022/day/5)
 */
class Day05 : AdventDay<String, Int> {
    override fun part1(input: String) = 0

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n\n")
        .let {
            val crateText = it[0]
            val moveText = it[1]
        }

    @JvmInline
    value class CrateYard(
        val stacks: List<Stack<String>>,
    ) {
        companion object {
            fun from(input: String) {
                val lines = input.split("\n")
                val crateLines = lines.dropLast(1).also { println(it) }
                val numberOfColumns = (lines.last().length / 3).also { println(it) }
                val crateYard = CrateYard((0 until numberOfColumns).map { Stack<String>() })

                crateLines
                    .asReversed()
                    .forEach { line ->
                        line.replace(" [", "[").also { println(it) }
                        Regex("   |\\[[A-Z]\\]").findAll(line)
                            .map { it.value }
                            .forEachIndexed { index, s ->
                                if(s.isNotBlank())
                                crateYard.stacks[index].push(s)
                            }
                    }
//                    .joinToString("|\n")
//                    .also { println(it) }
                println(crateYard)
            }
        }
    }
}