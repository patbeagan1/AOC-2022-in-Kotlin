package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Test
import org.testng.Assert

class Day08Test {
    private val sub = Day08()

    @Test
    fun part1() {
        val expected = 0
        val actual = sub.part1(Data.Day08Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun part2() {
    }

    @Test
    fun parseInput() {
    }
}