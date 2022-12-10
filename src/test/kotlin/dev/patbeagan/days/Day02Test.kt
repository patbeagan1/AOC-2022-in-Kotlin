package dev.patbeagan.days

import dev.patbeagan.days.Day02.Move
import dev.patbeagan.days.Day02.Round
import dev.patbeagan.obtainFile
import org.junit.Test
import org.testng.Assert

class Day02Test {
    private val sub = Day02()

    @Test
    fun `parsing works correctly`() {
        val expected = listOf(
            Round(Move.Paper, Move.Rock),
            Round(Move.Rock, Move.Paper),
            Round(Move.Scissors, Move.Scissors),
        )
        val actual: List<Round> = sub.parseInput(
            "Day02_input.txt"
                .obtainFile()
                .readText()
        )
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `sample answer`() {
        val actual = sub.part1(
            "Day02_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = 15
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1(
            "Day02_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = 8933
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 sample answer`() {
        val actual = sub.part2(
            "Day02_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = 12
        Assert.assertEquals(actual, expected)
    }
}