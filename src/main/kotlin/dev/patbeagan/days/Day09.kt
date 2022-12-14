package dev.patbeagan.days

import dev.patbeagan.days.Day09.Point.Companion.coord
import java.lang.IllegalArgumentException
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.sqrt

/**
 * [Day 9](https://adventofcode.com/2022/day/9)
 */
class Day09 : AdventDay<Int> {
    override fun part1(input: String) = parseInput(input).let { commands ->
        val rope = Rope()
        commands.forEach {
            rope.moveBy(it)
        }
        rope.print()
        rope.countPreviousTailPositions()
    }

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { s ->
            s
                .split(" ")
                .let {
                    val direction = it[0]
                    val amount = it[1].toInt()
                    when (direction) {
                        "U" -> Command.Up(amount)
                        "D" -> Command.Down(amount)
                        "L" -> Command.Left(amount)
                        "R" -> Command.Right(amount)
                        else -> throw IllegalArgumentException()
                    }
                }
        }

    sealed class Command(val amount: Int) {
        class Up(amount: Int) : Command(amount)
        class Down(amount: Int) : Command(amount)
        class Left(amount: Int) : Command(amount)
        class Right(amount: Int) : Command(amount)
    }

    class Rope {
        private var positionHead: Point = 0 coord 0
        private var positionTail: Point = 0 coord 0
        private val previousTailPositions = mutableListOf(positionTail)

        fun countPreviousTailPositions() = previousTailPositions.distinct().count()

        fun moveTo(dest: Point) {
            positionHead = dest
            if (positionTail.fartherThan1Cell(dest)) {
                val vector = positionTail.vectorTo(positionHead)
                positionTail = Point(
                    positionTail.x + vector.x.compareTo(0),
                    positionTail.y + vector.y.compareTo(0)
                )
                previousTailPositions.add(positionTail)
            }
        }

        fun moveBy(command: Command) {
            repeat(command.amount) {
                when (command) {
                    is Command.Down -> moveTo(positionHead.x coord positionHead.y + 1)
                    is Command.Left -> moveTo(positionHead.x - 1 coord positionHead.y)
                    is Command.Right -> moveTo(positionHead.x + 1 coord positionHead.y)
                    is Command.Up -> moveTo(positionHead.x coord positionHead.y - 1)
                }
            }
        }

        fun print() {
            println("$positionHead $positionTail ${positionHead.trueDistanceTo(positionTail)}")
            previousTailPositions.distinct().let { points ->
                val cols = points.maxBy { it.x }.x - points.minBy { it.x }.x
                val rows = points.maxBy { it.y }.y - points.minBy { it.y }.y
                for (y in 0 until rows) {
                    for (x in 0 until cols) {
                        if (points.contains(Point(x, y))) {
                            print("#")
                        } else {
                            print("-")
                        }
                    }
                    println()
                }
            }
        }

        private fun angle(destY: Int, destX: Int) = atan2(destY.toDouble(), destX.toDouble()) * (180 / Math.PI)
    }

    data class Vector(
        val x: Int = 0,
        val y: Int = 0,
    )

    data class Point(
        val x: Int = 0,
        val y: Int = 0,
    ) {
        fun trueDistanceTo(p: Point) = hypot(x.toDouble() - p.x, y.toDouble() - p.y)
        fun fartherThan1Cell(p: Point): Boolean =
            trueDistanceTo(p) > sqrt(2.0) // where sqrt(2.0) is the diagonal distance

        fun vectorTo(p: Point) = Vector(p.x - x, p.y - y)

        companion object {
            infix fun Int.coord(y: Int) = Point(this, y)
        }
    }
}