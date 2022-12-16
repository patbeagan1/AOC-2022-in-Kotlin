package dev.patbeagan

import java.io.File

object Data {
    //Day01
    val Day01Data get() = "Day01_data.txt".getData()
    val Day01Sample get() = "Day01_input.txt".getData()

    //Day02
    val Day02Data get() = "Day02_data.txt".getData()
    val Day02Sample get() = "Day02_input.txt".getData()

    //Day03
    val Day03Data get() = "Day03_data.txt".getData()
    val Day03Sample get() = "Day03_input.txt".getData()

    //Day04
    val Day04Data get() = "Day04_data.txt".getData()
    val Day04Sample get() = "Day04_input.txt".getData()

    //Day05
    val Day05Data get() = "Day05_data.txt".getData()
    val Day05Sample get() = "Day05_input.txt".getData()

    //Day06
    val Day06Data get() = "Day06_data.txt".getData()
    val Day06Sample get() = "Day06_input.txt".getData()

    //Day07
    val Day07Data get() = "Day07_data.txt".getData()
    val Day07Sample get() = "Day07_input.txt".getData()
    val Day07SampleOutput get() = "Day07_output.txt".getData()

    //Day08
    val Day08Data get() = "Day08_data.txt".getData()
    val Day08Sample get() = "Day08_input.txt".getData()

    //Day09
    val Day09Data get() = "Day09_data.txt".getData()
    val Day09Sample get() = "Day09_input.txt".getData()

    //Day10
    val Day10Data get() = "Day10_data.txt".getData()
    val Day10Sample get() = "Day10_input.txt".getData()

    //Day11
    val Day11Data get() = "Day11_data.txt".getData()
    val Day11Sample get() = "Day11_input.txt".getData()

    //Day12
    val Day12Data get() = "Day12_data.txt".getData()
    val Day12Sample get() = "Day12_input.txt".getData()

    /**
     * Reads lines from the given input txt file.
     */
    private fun String.obtainFile() = File("src", this)

    private fun String.getData() = obtainFile().readText()
}