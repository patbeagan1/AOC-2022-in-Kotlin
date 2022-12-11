package dev.patbeagan.days

import dev.patbeagan.Data
import dev.patbeagan.days.Day02.*
import org.junit.Test
import org.testng.Assert

class Day02Test {
    private val sub = Day02()

    @Test
    fun `parsing works correctly - part 1`() {
        val expected = listOf(
            Round(Move.Paper, Move.Rock),
            Round(Move.Rock, Move.Paper),
            Round(Move.Scissors, Move.Scissors),
        )
        val actual: List<Round> = sub.parseInput1(Data.Day02Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `parsing works correctly - part 2`() {
        val expected = listOf(
            Move.Rock to Outcome.Tie,
            Move.Paper to Outcome.Loss,
            Move.Scissors to Outcome.Win,
        )
        val actual = sub.parseInput2(Data.Day02Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `sample answer`() {
        val actual = sub.part1(Data.Day02Sample)
        val expected = 15
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1(Data.Day02Data)
        val expected = 8933
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 sample answer`() {
        val actual = sub.part2(Data.Day02Sample)
        val expected = 12
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val actual = sub.part2(Data.Day02Data)
        val expected = 11998
        Assert.assertEquals(actual, expected)
    }
}