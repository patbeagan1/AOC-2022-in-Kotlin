package dev.patbeagan.days

import dev.patbeagan.AdventDay
import jdk.jshell.spi.ExecutionControl.NotImplementedException

/**
 * [Day 10](https://adventofcode.com/2022/day/10)
 */
class Day10 : AdventDay<Int> {
    override fun part1(input: String): Int {
        val interestingSignalStrengths = mutableListOf(
            20, 60, 100, 140, 180, 220
        )
        var register = 1
        val signalSamples = mutableListOf<Int>()
        parseInput(input)
            .fold(0) { cycle, each ->
                println("$each\ncycle:$cycle register:$register")
                println(signalSamples)

                interestingSignalStrengths.firstOrNull {
                    ((cycle)..(cycle + each.cycles)).contains(it)
                }?.let {
                    signalSamples.add(register * it)
                    interestingSignalStrengths.remove(it)
                }

                when (each) {
                    is OpCode.Addx -> register += each.amount
                    OpCode.Noop -> Unit
                }

                cycle + each.cycles
            }
        println(signalSamples)
        return signalSamples.sum()
    }

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n")
        .map { s ->
            when {
                OpCode.Noop.regex.matches(s) -> OpCode.Noop
                OpCode.Addx.regex.matches(s) -> OpCode.Addx.regex
                    .find(s)
                    ?.groupValues
                    ?.get(1)
                    ?.toInt()!!.let { OpCode.Addx(it) }

                else -> throw NotImplementedException("This opcode is not supported.")
            }
        }
    
    sealed class OpCode(val cycles: Int) {
        class Addx(val amount: Int) : OpCode(2) {
            companion object {
                val regex = Regex("addx (-?\\d*)")
            }
        }

        object Noop : OpCode(1) {
            val regex = Regex("noop")
        }

        override fun toString(): String {
            val opCode = this
            return buildString {
                append(opCode.javaClass.simpleName)
                append(",")
                append(cycles)
                if (opCode is Addx) {
                    append(",")
                    append(opCode.amount)
                }
            }
        }
    }
}