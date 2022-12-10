package dev.patbeagan.days

import dev.patbeagan.obtainFile
import org.junit.Test
import org.testng.Assert

class Day01Test {
    private val sub = Day01()

    @Test
    fun `parse for elves completes correctly`() {
        val expected = listOf(
            Day01.Elf(
                listOf(
                    300,
                    4555,
                    65,
                )
            ),
            Day01.Elf(
                listOf(
                    345,
                    23,
                    1234,
                )
            )
        )
        val actual = sub.parseForElves(
            """
                300
                4555
                65
                
                345
                23
                1234
            """.trimIndent()
        )

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `sample answer`() {
        val actual = sub.part1("Day01_input.txt".obtainFile().readText())
        val expected = 24000
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1("Day01_data.txt".obtainFile().readText())
        val expected = 67027
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val actual = sub.part2("Day01_data.txt".obtainFile().readText())
        val expected = 197291
        Assert.assertEquals(actual, expected)
    }
}