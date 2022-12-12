package dev.patbeagan

import java.io.File

object Data {
    //Day01
    val Day01Data get() = "Day01_data.txt".obtainFile().readText()
    val Day01Sample get() = "Day01_input.txt".obtainFile().readText()

    //Day02
    val Day02Data get() = "Day02_data.txt".obtainFile().readText()
    val Day02Sample get() = "Day02_input.txt".obtainFile().readText()

    //Day03
    val Day03Data get() = "Day03_data.txt".obtainFile().readText()
    val Day03Sample get() = "Day03_input.txt".obtainFile().readText()

    //Day04
    val Day04Data get() = "Day04_data.txt".obtainFile().readText()
    val Day04Sample get() = "Day04_input.txt".obtainFile().readText()

    //Day05
    val Day05Data get() = "Day05_data.txt".obtainFile().readText()
    val Day05Sample get() = "Day05_input.txt".obtainFile().readText()

    //Day06
    val Day06Data get() = "Day06_data.txt".obtainFile().readText()
    val Day06Sample get() = "Day06_input.txt".obtainFile().readText()

    //Day07
    val Day07Data get() = "Day07_data.txt".obtainFile().readText()
    val Day07Sample get() = "Day07_input.txt".obtainFile().readText()
    val Day07SampleOutput get() = "Day07_output.txt".obtainFile().readText()

    //Day08
    val Day08Data get() = "Day08_data.txt".obtainFile().readText()
    val Day08Sample get() = "Day08_input.txt".obtainFile().readText()

    /**
     * Reads lines from the given input txt file.
     */
    private fun String.obtainFile() = File("src", this)
}