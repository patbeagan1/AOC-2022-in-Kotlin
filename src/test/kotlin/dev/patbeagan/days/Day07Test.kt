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
}