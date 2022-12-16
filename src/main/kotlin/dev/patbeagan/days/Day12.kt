package dev.patbeagan.days

import dev.patbeagan.List2D
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

/**
 * [Day 12](https://adventofcode.com/2022/day/12)
 */
class Day12 : AdventDay<Int> {
    override fun part1(input: String): Int {
        val heightMap = parseInput(input)
        val graph = heightMap.asGraph()
        val path = DijkstraShortestPath(graph)
            .getPath(
                heightMap.start,
                heightMap.end
            )
        val length = path.length
        return -1
    }

    override fun part2(input: String) = 0

    fun parseInput(input: String): HeightMap {
        var start: HeightMap.Tile? = null
        var end: HeightMap.Tile? = null
        return input
            .trim()
            .split("\n")
            .map { strings ->
                strings.map { char ->
                    HeightMap.Tile(char).also {
                        when (it.token) {
                            'S' -> start = it
                            'E' -> end = it
                        }
                    }
                }
            }.let { list ->
                HeightMap(start!!, end!!, List2D(list)).also { println(it) }
            }
    }

    class HeightMap(
        val start: Tile,
        val end: Tile,
        private val value: List2D<Tile>
    ) {

        init {
            assert(value.grid.distinctBy {
                it.size
            }.size == 1)
        }

        private fun getNeighbors(x: Int, y: Int) = Neighborhood(
            value.grid.getOrNull(y)?.getOrNull(x),
            value.grid.getOrNull(y)?.getOrNull(x - 1),
            value.grid.getOrNull(y - 1)?.getOrNull(x),
            value.grid.getOrNull(y)?.getOrNull(x + 1),
            value.grid.getOrNull(y + 1)?.getOrNull(x),
        )

        fun asGraph(): SimpleGraph<Tile, DefaultEdge> =
            SimpleGraph<Tile, DefaultEdge>(DefaultEdge::class.java).apply {
                value.traverse { each, _, _ ->
                    addVertex(each)
                }

                value.traverse { each, x, y ->
                    getNeighbors(x, y).run {
                        left
                            ?.takeIf { each.token.code + 1 >= it.token.code }
                            ?.let { addEdge(self, it) }
                        top
                            ?.takeIf { each.token.code + 1 >= it.token.code }
                            ?.let { addEdge(self, it) }
                        right
                            ?.takeIf { each.token.code + 1 >= it.token.code }
                            ?.let { addEdge(self, it) }
                        bottom
                            ?.takeIf { each.token.code + 1 >= it.token.code }
                            ?.let { addEdge(self, it) }
                    }
                }
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