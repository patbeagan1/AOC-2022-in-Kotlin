package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Assert
import org.junit.Test

class Day10Test {
    val sub = Day10()

    @Test
    fun `part 1 sample answer`() {
        val expected = 13140
        val actual = sub.part1(Data.Day10Sample)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 16020
        val actual = sub.part1(Data.Day10Data)
        Assert.assertEquals(expected, actual)
    }
}