package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Test
import org.testng.Assert

class Day08Test {
    private val sub = Day08()

    @Test
    fun `part 1 sample`() {
        val expected = 21
        val actual = sub.part1(Data.Day08Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 1717
        val actual = sub.part1(Data.Day08Data)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 sample`() {
        val expected = 8
        val actual = sub.part2(Data.Day08Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val expected = 321975
        val actual = sub.part2(Data.Day08Data)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun parseInput() {
        throw NotImplementedError()
    }
}