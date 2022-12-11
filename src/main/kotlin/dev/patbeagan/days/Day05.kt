package dev.patbeagan.days

import dev.patbeagan.AdventDay
import java.util.EmptyStackException
import java.util.Stack

/**
 * [Day 5](https://adventofcode.com/2022/day/5)
 */
class Day05 : AdventDay<String> {
    override fun part1(input: String): String {
        val (crateYard, moveCommands) = parseInput(input)
        moveCommands.forEach {
            try {
                for (i in 0 until it.amount) {
                    crateYard.stacks[it.to - 1].push(crateYard.stacks[it.from - 1].pop())
                }
                println(crateYard)
            } catch (e: Exception) {
                println()
            }
        }
        return crateYard.stacks.joinToString("") {
            try {
                it.peek()
            } catch (e: EmptyStackException) {
                " "
            }
        }
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
        override fun toString(): String = stacks.joinToString("\n") {
            it.toString()
        }

        companion object {
            fun from(input: String): CrateYard {
                val lines = input.split("\n")
                val crateLines = lines.dropLast(1)
                val numberOfColumns = getColumns(lines)
                val crateYard = CrateYard((0 until numberOfColumns).map { Stack<String>() })
                val map: List<List<Char>> = crateLines
                    .asReversed()
                    .onEach { it.also { println(it) } }
                    .map { s ->
                        s
                            .chunked(4)
                            .map { it[1] }
                    }
                map.forEach { each ->
                    each.forEachIndexed { index, it ->
                        if (it in CharRange('A', 'Z')) {
                            crateYard.stacks[index].push(it.toString())
                        }
                    }
                }
                println(crateYard)
                return crateYard
            }

            private fun getColumns(lines: List<String>): Int = lines
                .last()
                // todo calculate this directly instead of chunking - too memory intensive
                .chunked(4)
                .size
        }
    }
}