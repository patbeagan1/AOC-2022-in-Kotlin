package dev.patbeagan.days

import dev.patbeagan.Data
import dev.patbeagan.days.Day07.Dir
import dev.patbeagan.days.Day07.File
import org.junit.Assert
import org.junit.Test

class Day07Test {
    private val sub = Day07()

    @Test
    fun `parsing works as expected - manual`() {
        val expected = Dir(
            name = "/",
            files = mutableListOf(
                Dir(
                    name = "a",
                    files = mutableListOf(
                        Dir(
                            name = "e",
                            files = mutableListOf(
                                File(name = "i", size = 584),
                                File(name = "f", size = 29116),
                                File(name = "g", size = 2557),
                                File(name = "h.lst", size = 62596),
                                File(name = "b.txt", size = 14848514),
                                File(name = "c.dat", size = 8504156),
                                Dir(
                                    name = "d", files = mutableListOf(
                                        File(name = "j", size = 4060174),
                                        File(name = "d.log", size = 8033020),
                                        File(name = "d.ext", size = 5626152),
                                        File(name = "k", size = 7214296)
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
        val actual = sub.parseInput(Data.Day07Sample)
        Assert.assertEquals(expected.prettyFormat(), actual!!.prettyFormat())
    }

    @Test
    fun `parsing works as expected`() {
        val expected = Data.Day07SampleOutput
        val actual = sub.parseInput(Data.Day07Sample)
        Assert.assertEquals(expected, actual!!.prettyFormat())
    }
}