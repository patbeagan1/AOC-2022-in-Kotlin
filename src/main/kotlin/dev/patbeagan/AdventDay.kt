package dev.patbeagan

interface AdventDay<OUTPUT> {
    fun part1(input: String): OUTPUT
    fun part2(input: String): OUTPUT
}
///**
// * [Day 1](https://adventofcode.com/2022/day/1)
// */
//class Day01 : AdventDay<Int> {
//    override fun part1(input: String) = 0
//
//    override fun part2(input: String) = 0
//
//    fun parseInput(input: String) = input
//        .trim()
//        .split("\n")
//}