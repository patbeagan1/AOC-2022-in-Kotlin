package dev.patbeagan.days

import dev.patbeagan.AdventDay
import java.util.*

/**
 * [Day 7](https://adventofcode.com/2022/day/7)
 */
class Day07 : AdventDay<Int> {
    override fun part1(input: String): Int {
        val dir = parseInput(input)
        val listOfDirsOfSmallSize = mutableListOf<Dir>()
        dir.walk {
            if (it is Dir && it.size < 100000) {
                listOfDirsOfSmallSize.add(it)
            }
        }
        return listOfDirsOfSmallSize.sumOf { it.size }
    }

    override fun part2(input: String): Int {
        val maxSpace = 70_000_000
        val updateSpace = 30_000_000
        val dir = parseInput(input)
        val totalSpace = dir.size
        val freeSpace = maxSpace - totalSpace
        val requiredSpace = updateSpace - freeSpace
        val listOfDirs = mutableListOf<Dir>()
        dir.walk {
            if (it is Dir) {
                listOfDirs.add(it)
            }
        }
        return listOfDirs.minBy {
            if (it.size < requiredSpace) Int.MAX_VALUE else it.size
        }.size
    }

    fun parseInput(input: String): Dir {
        val lines = input
            .trim()
            .split("\n")
        val context = Context()
        val commands = mutableListOf<Command>()

        lines.forEach { line ->
            if (Command.isCommand(line)) {
                Command.fromString(context, line)
                    ?.also { it.emit() }
                    ?.let { commands.add(it) }
            } else {
                commands.last().scan(line)
            }
        }
        context.fileSystem.prettyFormat()
        return context.fileSystem
    }

    data class Context(
        var location: Stack<Dir> = Stack()
    ) {
        val fileSystem: Dir get() = location.first()
    }

    sealed class Command {
        abstract val context: Context

        class CommandCD(override val context: Context, val input: String) : Command() {
            override fun emit() = performAction()
            override fun scan(input: String) = performAction()
            private fun performAction() {
                val dirName = input
                    .split(" ")[1]

                when (dirName) {
                    ".." -> context.location.pop()
                    else -> {
                        val currentDir: Dir? = try {
                            context.location.peek()
                        } catch (e: EmptyStackException) {
                            null
                        }
                        val matchingDir: Dir? = currentDir
                            ?.files
                            ?.firstOrNull { it is Dir && it.name == dirName }
                            ?.let { it as Dir }
                        val dir = matchingDir ?: Dir(name = dirName, mutableListOf())
                        context
                            .location
                            .push(dir)
                    }
                }
            }
        }

        class CommandLS(override val context: Context, val input: String) : Command() {
            override fun emit() {
                context.location.peek()?.files?.forEach {
                    when (it) {
                        is Dir -> println("dir ${it.name}")
                        is File -> println("${it.size} ${it.name}")
                    }
                }
            }

            override fun scan(input: String) {
                println("ls: $input")
                when {
                    regexFile.matches(input) -> regexFile.find(input)?.groupValues
                        ?.let { File(it[2], it[1].toInt()) }
                        ?.let { addToFileTree(it) }
                        ?.also { println("scanned file") }

                    regexDir.matches(input) -> regexDir.find(input)?.groupValues
                        ?.let { Dir(it[1], mutableListOf()) }
                        ?.let { addToFileTree(it) }
                        ?.also { println("scanned dir") }

                    else -> Unit
                }
            }

            private fun addToFileTree(it: INode) = context.location.peek()?.files?.add(it)

            companion object {
                val regexFile = "(\\d*) *([^ ]*)".toRegex()
                val regexDir = "dir *([^ ]*)".toRegex()
            }
        }

        abstract fun emit()
        abstract fun scan(input: String)

        companion object {
            private val commandRegex = "\\$ *\\W*(.*)".toRegex()
            fun isCommand(input: String) = commandRegex.matches(input)

            fun fromString(context: Context, input: String) = commandRegex
                .find(input)
                .let { it?.groupValues?.get(1) }
                ?.let {
                    when {
                        it.startsWith("cd") -> CommandCD(context, it)
                        it.startsWith("ls") -> CommandLS(context, it)
                        else -> null
                    }
                }
        }
    }

    data class Dir(
        override val name: String,
        val files: MutableList<INode>
    ) : INode {
        override val size: Int get() = files.sumOf { it.size }
        fun walk(action: (INode) -> Unit) {
            files.forEach {
                when (it) {
                    is File -> action(it)
                    is Dir -> {
                        action(it)
                        it.walk(action)
                    }
                }
            }
        }

        fun prettyFormat(
            indentation: Int = 0,
            stringBuilder: StringBuilder = StringBuilder()
        ): String {
            val singleIndent = "  "
            val indentString = buildString { repeat(indentation) { append(singleIndent) } }
            return stringBuilder.apply {
                stringBuilder.appendLine("${indentString}- $name (dir)")
                files.forEach {
                    when (it) {
                        is Dir -> it.prettyFormat(indentation + 1, stringBuilder)
                        is File -> stringBuilder.appendLine("${indentString}$singleIndent- ${it.name} (file, size=${it.size})")
                    }
                }
            }.toString().trim()
        }

        fun prettyPrint() = println(prettyFormat())
    }

    data class File(
        override val name: String,
        override val size: Int
    ) : INode

    sealed interface INode {
        val name: String
        val size: Int
    }
}