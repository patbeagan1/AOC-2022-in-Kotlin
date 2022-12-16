package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Assert
import org.junit.Test

class Day12Test {
    val sub = Day12()

    @Test
    fun `part 1 sample answer`() {
        val expected = 10605
        val actual = sub.part1(Data.Day12Sample)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 121203
        val actual = sub.part1(Data.Day12Data)
        Assert.assertEquals(expected, actual)
    }
}