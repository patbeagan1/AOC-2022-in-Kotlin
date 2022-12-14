package dev.patbeagan.days

interface AdventDay<OUTPUT> {
    fun part1(input: String): OUTPUT
    fun part2(input: String): OUTPUT
}