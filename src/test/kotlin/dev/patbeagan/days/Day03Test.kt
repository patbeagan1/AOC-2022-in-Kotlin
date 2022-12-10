package dev.patbeagan.days

import dev.patbeagan.days.Day03.*
import dev.patbeagan.obtainFile
import org.junit.Test
import org.testng.Assert

class Day03Test {
    private val sub = Day03()

    @Test
    fun `parsing works correctly`() {
        val expected = listOf(
            Rucksack("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
            Rucksack("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"),
            Rucksack("PmmdzqPrV", "vPwwTWBwg"),
            Rucksack("wMqvLMZHhHMvwLH", "jbvcjnnSBnvTQFn"),
            Rucksack("ttgJtRGJ", "QctTZtZT"),
            Rucksack("CrZsJsPPZsGz", "wwsLwLmpwMDw")
        )
        val actual = sub.parseInput("Day03_input.txt".obtainFile().readText())
        println(actual)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `sample answer`() {
        val actual = sub.part1(
            "Day03_input.txt"
                .obtainFile()
                .readText()
        )
        val expected = 157
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val actual = sub.part1(
            "Day03_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = 7821
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val actual = sub.part2(
            "Day03_data.txt"
                .obtainFile()
                .readText()
        )
        val expected = 2752
        Assert.assertEquals(actual, expected)
    }
}