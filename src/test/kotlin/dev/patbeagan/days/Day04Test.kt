package dev.patbeagan.days

import dev.patbeagan.days.Day04.*
import dev.patbeagan.obtainFile
import org.junit.Test
import org.testng.Assert

class Day04Test {
    private val sub = Day04()

    @Test
    fun `parsing works correctly`() {
        val expected = listOf(
            CampSections(range = 2..4) to CampSections(range = 6..8),
            CampSections(range = 2..3) to CampSections(range = 4..5),
            CampSections(range = 5..7) to CampSections(range = 7..9),
            CampSections(range = 2..8) to CampSections(range = 3..7),
            CampSections(range = 6..6) to CampSections(range = 4..6),
            CampSections(range = 2..6) to CampSections(range = 4..8)
        )
        val actual = sub.parseInput("Day04_input.txt".obtainFile().readText())
        println(actual)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 sample answer`() {
        val actual = sub.part1(
            "Day04_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = 2
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1(
            "Day04_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = 569
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 sample answer`() {
        val actual = sub.part2(
            "Day04_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = 4
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val actual = sub.part2(
            "Day04_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = 936
        Assert.assertEquals(actual, expected)
    }
}
