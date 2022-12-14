package dev.patbeagan.days

import dev.patbeagan.CanTraverse
import dev.patbeagan.takeUntil

/**
 * [Day 8](https://adventofcode.com/2022/day/8)
 */
class Day08 : AdventDay<Int> {
    override fun part1(input: String) = parseInput(input)
        .let { treeGrid ->
            var count = 0
            treeGrid.walk { _, x, y ->
                if (treeGrid.isTreeVisibleAt(x, y)) {
                    count++
                }
            }
            count
        }

    override fun part2(input: String): Int {
        val treeGrid = parseInput(input)
        val scenicScores = mutableListOf<Int>()
        treeGrid.walk { _, x, y ->
            treeGrid.scenicScoreAt(x, y).let { scenicScores.add(it) }
        }
        return scenicScores.max()
    }

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { s -> s.map { Tree(it.toString().toInt()) } }
        .let { TreeGrid(it) }
        .also { println(it) }

    @JvmInline
    value class Tree(val height: Int)

    @JvmInline
    value class TreeGrid(private val trees: List<List<Tree>>) : CanTraverse {
        fun isTreeVisibleAt(x: Int, y: Int): Boolean {
            val tree = trees[y][x]
            TreeScanner(trees)
                .getLanes(x, y)
                .run {
                    if (left.all { it.height < tree.height }) return true
                    if (right.all { it.height < tree.height }) return true

                    if (top.all { it.height < tree.height }) return true
                    if (bottom.all { it.height < tree.height }) return true

                    return false
                }
        }

        fun walk(action: (tree: Tree, x: Int, y: Int) -> Unit) = trees.traverse(action)

        fun scenicScoreAt(x: Int, y: Int): Int {
            val tree = trees[y][x]
            TreeScanner(trees)
                .getLanes(x, y)
                .run {
                    return listOf(
                        left.asReversed().takeUntil { it.height < tree.height }.count(),
                        top.asReversed().takeUntil { it.height < tree.height }.count(),
                        right.takeUntil { it.height < tree.height }.count(),
                        bottom.takeUntil { it.height < tree.height }.count(),
                    ).fold(1) { acc, each -> each * acc }
                }
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

        @JvmInline
        value class TreeScanner(private val trees: List<List<Tree>>) {
            fun getLanes(
                x: Int,
                y: Int
            ): TreeLane {
                val row = trees[y]
                val col = trees.map { it[x] }

                val (left, right) = splitTrees(row, x)
                val (top, bottom) = splitTrees(col, y)

                return TreeLane(left, top, right, bottom)
            }

            private fun splitTrees(list: List<Tree>, pivot: Int): Pair<List<Tree>, List<Tree>> {
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

            data class TreeLane(
                val left: List<Tree>,
                val top: List<Tree>,
                val right: List<Tree>,
                val bottom: List<Tree>
            ) {
                fun printLanes() {
                    val indent = buildString { repeat(left.size) { append(" ") } }
                    for (i in top) {
                        println(indent + i.height)
                    }
                    for (i in left) {
                        print(i.height)
                    }
                    print("X")
                    for (i in right) {
                        print(i.height)
                    }
                    print("\n")
                    for (i in bottom) {
                        println(indent + i.height)
                    }
                    println()
                }
            }
        }
    }
}