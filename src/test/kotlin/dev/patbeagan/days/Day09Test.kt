package dev.patbeagan.days

import dev.patbeagan.Data
import dev.patbeagan.days.Day09.Point.Companion.coord
import org.junit.Assert
import org.junit.Test

class Day09Test {
    private val sub = Day09()

    @Test
    fun `rope moves correctly`() {
        Day09
            .Rope().also {
                it.moveTo(1 coord 1)
                it.moveTo(2 coord 2)
                it.moveTo(1 coord 1)
                it.moveTo(2 coord 1)
                it.moveTo(3 coord 1)
            }
            .also { it.print() }
    }

    @Test
    fun `part 1 sample answer`() {
        val expected = 13
        val actual = sub.part1(Data.Day09Sample)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 5695
        val actual = sub.part1(Data.Day09Data)
        Assert.assertEquals(expected, actual)
    }
}