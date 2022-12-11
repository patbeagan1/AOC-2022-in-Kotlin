package dev.patbeagan.days

import dev.patbeagan.Data
import org.junit.Test
import org.testng.Assert

class Day06Test {
    private val sub = Day06()

    @Test
    fun `parsing works correctly`() {
        val expected = listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        )
        val actual = sub.parseInput(Data.Day06Sample)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `packet_mjqjpqmgbljsphdztnvjfqwrcgsmlb`() {
        val expected = 7
        val input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        val actual = sub.part1(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun packet_bvwbjplbgvbhsrlpgdmjqwftvncz() {
        val input = "bvwbjplbgvbhsrlpgdmjqwftvncz"
        val expected = 5
        val actual = sub.part1(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun packet_nppdvjthqldpwncqszvftbrmjlhg() {
        val input = "nppdvjthqldpwncqszvftbrmjlhg"
        val expected = 6
        val actual = sub.part1(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun packet_nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg() {
        val input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        val expected = 10
        val actual = sub.part1(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun packet_zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw() {
        val input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        val expected = 11
        val actual = sub.part1(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `message_mjqjpqmgbljsphdztnvjfqwrcgsmlb`() {
        val expected = 19
        val input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        val actual = sub.part2(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun message_bvwbjplbgvbhsrlpgdmjqwftvncz() {
        val input = "bvwbjplbgvbhsrlpgdmjqwftvncz"
        val expected = 23
        val actual = sub.part2(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun message_nppdvjthqldpwncqszvftbrmjlhg() {
        val input = "nppdvjthqldpwncqszvftbrmjlhg"
        val expected = 23
        val actual = sub.part2(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun message_nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg() {
        val input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        val expected = 29
        val actual = sub.part2(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun message_zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw() {
        val input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        val expected = 26
        val actual = sub.part2(input)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 1 answer`() {
        val expected = 1804
        val actual = sub.part1(Data.Day06Data)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `part 2 answer`() {
        val expected = 2508
        val actual = sub.part2(Data.Day06Data)
        Assert.assertEquals(actual, expected)
    }
}


