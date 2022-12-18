package dev.patbeagan.days

/**
 * [Day 13](https://adventofcode.com/2022/day/13)
 */
class Day13 : AdventDay<Int> {
    override fun part1(input: String) = 0

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n")

    @JvmInline
    value class PacketPair(val value: Pair<Packet<List<*>>, Packet<List<*>>>)

    @JvmInline
    value class Packet<T>(private val value: List<T>) //: Comparable<Packet<*>> {
//        override fun compareTo(other: Packet<*>): Int {
//            when {
//                value.firstOrNull() is Int && other.value.firstOrNull() is Int -> {
//
//                    return
//                }
//                value.firstOrNull() is List<*> && other.value.firstOrNull() is Int -> {
//
//
//                }
//                value.firstOrNull() is Int && other.value.firstOrNull() is List<*> -> {
//
//
//                }
//                value.firstOrNull() is List<*> && other.value.firstOrNull() is List<*> -> {
//
//
//                }
//            }
//        }
 //   }
}