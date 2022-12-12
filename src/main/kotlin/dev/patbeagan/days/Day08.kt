package dev.patbeagan.days

import dev.patbeagan.AdventDay

/**
 * [Day 8](https://adventofcode.com/2022/day/8)
 */
class Day08 : AdventDay<Int> {
    override fun part1(input: String) = parseInput(input).countVisibleTrees()

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { s -> s.map { Tree(it.toString().toInt()) } }
        .let { TreeGrid(it) }
        .also { println(it) }

    @JvmInline
    value class Tree(val height: Int)

    @JvmInline
    value class TreeGrid(private val trees: List<List<Tree>>) {
        fun isTreeVisibleAt(x: Int, y: Int): Boolean {
            val tree = trees[y][x]
            val row = trees[y]
            val col = trees.map { it[x] }

            val (left, right) = splitTrees(col, x)
            if (left.all { it.height < tree.height }) return true
            if (right.all { it.height < tree.height }) return true

            val (top, bottom) = splitTrees(row, y)
            if (top.all { it.height < tree.height }) return true
            if (bottom.all { it.height < tree.height }) return true

            return false
        }

        fun countVisibleTrees(): Int {
            var count = 0
            trees.forEachIndexed { indexY, each ->
                each.forEachIndexed { indexX, tree ->
                    if (isTreeVisibleAt(indexX, indexY)) {
                        count++
                    }
                }
            }
            return count
        }

        override fun toString(): String = buildString {
            trees.forEachIndexed { indexY, each ->
                each.forEachIndexed { indexX, _ ->
                    if (isTreeVisibleAt(indexX, indexY)) {
                        append("X")
                    } else {
                        append("-")
                    }
                }
                appendLine()
            }
            appendLine("-----")
            trees.forEach { eachRow ->
                eachRow.forEach { each ->
                    append(each.height)
                }
                appendLine()
            }
        }

        private fun splitTrees(list: List<Tree>, pivot: Int): Pair<MutableList<Tree>, MutableList<Tree>> {
            val prevList = mutableListOf<Tree>()
            val nextList = mutableListOf<Tree>()
            list.forEachIndexed { index, each ->
                when {
                    index < pivot -> prevList.add(each)
                    index > pivot -> nextList.add(each)
                }
            }
            return prevList to nextList
        }
    }
}