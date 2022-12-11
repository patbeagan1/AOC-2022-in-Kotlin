package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Assert
import org.junit.Test

class Day07Test {
    private val sub = Day07()

    @Test
    fun `parsing works as expected`() {
        val expected = Data.Day07SampleOutput
        val actual = sub.parseInput(Data.Day07Sample)
        Assert.assertEquals(expected, actual!!.prettyFormat())
    }

    @Test
    fun `sample answer`() {
        val expected = 95437
        val actual = sub.part1(Data.Day07Sample)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 1390824
        val actual = sub.part1(Data.Day07Data)
        Assert.assertEquals(expected, actual)
    }
}