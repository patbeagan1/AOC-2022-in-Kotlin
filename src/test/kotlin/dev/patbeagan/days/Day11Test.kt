package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Assert
import org.junit.Test

class Day11Test {
    val sub = Day11()

    @Test
    fun `part 1 sample answer`() {
        val expected = -1
        val actual = sub.part1(Data.Day11Sample)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `part 1 answer`() {
        val expected = -1
        val actual = sub.part1(Data.Day11Data)
        Assert.assertEquals(expected, actual)
    }
}