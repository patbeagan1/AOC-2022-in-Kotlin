package dev.patbeagan

interface AdventDay<INPUT, OUTPUT> {
    fun part1(input: INPUT): OUTPUT
    fun part2(input: INPUT): OUTPUT
}

///**
// * [Day 1](https://adventofcode.com/2022/day/1)
// */
//class Day01 : AdventDay<String, Int> {
//    override fun part1(input: String) = 0
//
//    override fun part2(input: String) = 0
//
//    fun parseInput(input: String) = input
//        .trim()
//        .split("\n")
//}