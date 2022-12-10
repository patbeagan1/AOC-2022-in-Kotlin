package dev.patbeagan.days

import dev.patbeagan.AdventDay
import java.util.Stack

/**
 * [Day 5](https://adventofcode.com/2022/day/5)
 */
class Day05 : AdventDay<String, String> {
    override fun part1(input: String): String {
        val (crateYard, moveCommands) = parseInput(input)
        moveCommands.forEach {
            for (i in 0 until it.amount) {
                crateYard.stacks[it.to - 1].push(crateYard.stacks[it.from - 1].pop())
            }
        }
        return crateYard.stacks.map { it.peek() }.joinToString("")
    }

    override fun part2(input: String) = ""

    fun parseInput(input: String) = input
        .split("\n\n")
        .let { sections ->
            val crateYard = CrateYard.from(sections[0])
            val moveCommands = sections[1]
                .split("\n")
                .filter { !it.isNullOrBlank() }
                .map { MoveCommand.from(it) }

            crateYard to moveCommands
        }

    data class MoveCommand(
        val amount: Int,
        val from: Int,
        val to: Int,
    ) {
        companion object {
            fun from(input: String) = MoveCommand(
                Regex("move (\\d)").find(input)!!.groupValues[1].toInt(),
                Regex("from (\\d)").find(input)!!.groupValues[1].toInt(),
                Regex("to (\\d)").find(input)!!.groupValues[1].toInt()
            )
        }
    }

    @JvmInline
    value class CrateYard(
        val stacks: List<Stack<String>>,
    ) {
        companion object {
            fun from(input: String): CrateYard {
                val lines = input.split("\n")
                val crateLines = lines.dropLast(1)
                val numberOfColumns = (lines.last().length / 3)
                val crateYard = CrateYard((0 until numberOfColumns).map { Stack<String>() })

                crateLines
                    .asReversed()
                    .forEach { line ->
                        debugShow(line)
                        Regex("   |\\[([A-Z])\\]").findAll(line)
                            .map { it.groupValues[1] }
                            .forEachIndexed { index, s ->
                                if (s.isNotBlank()) {
                                    crateYard.stacks[index].push(s)
                                }
                            }
                    }
                return crateYard
            }

            private fun debugShow(line: String) {
                line.replace(" [", "[").also { println(it) }
            }
        }
    }
}