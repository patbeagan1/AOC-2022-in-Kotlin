package dev.patbeagan.days

import dev.patbeagan.List2D
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import java.util.*

/**
 * [Day 12](https://adventofcode.com/2022/day/12)
 */
class Day12 : AdventDay<Int> {
    override fun part1(input: String): Int {
        parseInput(input)

        return -1
    }

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { strings ->
            strings.map {
                HeightMap.Tile(it)
            }
        }.let { list ->
            HeightMap(List2D(list)).also { println(it) }
        }

    @JvmInline
    value class HeightMap(private val value: List2D<Tile>) {
        init {
            assert(value.grid.distinctBy {
                it.size
            }.size == 1)
        }

        fun getNeighbors(x: Int, y: Int) = Neighborhood(
            value.grid.getOrNull(y)?.getOrNull(x),
            value.grid.getOrNull(y)?.getOrNull(x - 1),
            value.grid.getOrNull(y - 1)?.getOrNull(x),
            value.grid.getOrNull(y)?.getOrNull(x + 1),
            value.grid.getOrNull(y + 1)?.getOrNull(x),
        )

        fun asGraph() {
            return SimpleGraph<Tile, DefaultEdge>(DefaultEdge::class.java)
        }

        @JvmInline
        value class Tile(val token: Char)

        data class Neighborhood(
            val self: Tile?,
            val left: Tile?,
            val top: Tile?,
            val right: Tile?,
            val bottom: Tile?,
        )

    }
}