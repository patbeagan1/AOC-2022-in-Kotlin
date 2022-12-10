package dev.patbeagan.days

import dev.patbeagan.AdventDay
import dev.patbeagan.days.Day02.Move.*
import dev.patbeagan.days.Day02.Outcome.*

/**
 * [Day 2](https://adventofcode.com/2022/day/2)
 */
class Day02 : AdventDay<String, Int> {
    override fun part1(input: String) =
        parseInput(input)
            .fold(0) { acc, each ->
                acc + Outcome.fromRound(each).points + each.self.points
            }

    override fun part2(input: String): Int = parseInput(input)
        .fold(0) { acc, each ->
            acc + Outcome.fromRound(each).points + each.self.points
        }

    fun parseInput(input: String): List<Round> = input
        .trim()
        .split("\n")
        .map { s ->
            s.split(
                " "
            ).map {
                Move.from(it)!!
            }.let {
                Round(it[1], it[0])
            }
        }

    data class Round(val self: Move, val opponent: Move)

    enum class Move(
        val points: Int,
    ) {
        Rock(1),
        Paper(2),
        Scissors(3);
        
        companion object {
            private val byToken = mapOf(
                "A" to Rock,
                "B" to Paper,
                "C" to Scissors,
                "X" to Rock,
                "Y" to Paper,
                "Z" to Scissors,
            )

            fun from(input: String): Move? = byToken[input]
        }
    }

    enum class Outcome(val points: Int) {
        Win(6),
        Tie(3),
        Loss(0);

        companion object {
            fun fromRound(round: Round) = when {
                round.self == round.opponent -> Tie
                winStates.contains(round) -> Win
                else -> Loss
            }

            private val winStates = listOf(
                Round(Scissors, Paper),
                Round(Paper, Rock),
                Round(Rock, Scissors)
            )
        }
    }
}