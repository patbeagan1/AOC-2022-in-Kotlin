package dev.patbeagan.days

import dev.patbeagan.days.Day05.*
import dev.patbeagan.obtainFile
import org.junit.Test
import org.testng.Assert
import java.util.Stack

class Day05Test {
    private val sub = Day05()

    private fun List<String>.toStack(): Stack<String> {
        val stack = Stack<String>()
        this.forEach { stack.push(it) }
        return stack
    }

    @Test
    fun `parsing works correctly`() {
        val actual = sub.parseInput("Day05_input.txt".obtainFile().readText())
        println(actual)
        Assert.assertEquals(
            actual.first,
            CrateYard(
                stacks = listOf(
                    listOf("Z", "N").toStack(),
                    listOf("M", "C", "D").toStack(),
                    listOf("P").toStack()
                )
            )
        )

        Assert.assertEquals(
            actual.second,
            listOf(
                MoveCommand(amount = 1, from = 2, to = 1),
                MoveCommand(amount = 3, from = 1, to = 3),
                MoveCommand(amount = 2, from = 2, to = 1),
                MoveCommand(amount = 1, from = 1, to = 2),
            )
        )
    }

    @Test
    fun `part 1 sample answer`() {
        val actual = sub.part1(
            "Day05_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = "CMZ"
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1(
            "Day05_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = "HDDMGNVG "
        Assert.assertEquals(actual, expected)
    }
}
