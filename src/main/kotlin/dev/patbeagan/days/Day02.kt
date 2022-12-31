package dev.patbeagan.days

import dev.patbeagan.days.Day02.Move.*

/**
 * [Day 2](https://adventofcode.com/2022/day/2)
 */
class Day02 : AdventDay<Int> {
    override fun part1(input: String) = parseInput1(input)
        .fold(0) { acc, each ->
            val movePoints = each.self.points
            val outcomePoints = Outcome.fromRound(each).points
            acc + outcomePoints + movePoints
        }

    override fun part2(input: String): Int = parseInput2(input)
        .fold(0) { acc, each ->
            val ensuringMoveFrom = each.second.getEnsuringMoveFrom(each.first)
            val movePoints = ensuringMoveFrom.points
            val outcomePoints = each.second.points
            acc + movePoints + outcomePoints
        }

    fun parseInput1(input: String): List<Round> = getLines(input)
        .map { s ->
            s.split(" ")
                .map { Move.from(it)!! }
                .let { Round(it[1], it[0]) }
        }

    fun parseInput2(input: String): List<Pair<Move, Outcome>> = getLines(input)
        .map { s ->
            s.split(" ")
                .let { Move.from(it[0])!! to Outcome.from(it[1])!! }
        }

    private fun getLines(input: String) = input
        .trim()
        .split("\n")

    /**
     * A single round of rock-paper-scissors.
     */
    data class Round(val self: Move, val opponent: Move)

    /**
     * A single move.
     */
    enum class Move(val points: Int) {
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

    /**
     * The outcome of a round.
     */
    enum class Outcome(val points: Int) {
        Win(6),
        Tie(3),
        Loss(0);

        /**
         * Given an end state and the opponent's move,
         * this returns the move that you need to do to get to that end state
         */
        fun getEnsuringMoveFrom(move: Move): Move = when (this) {
            Win -> winStates.first { it.opponent == move }.self
            Tie -> move
            Loss -> winStates.first { it.self == move }.opponent
        }

        companion object {
            private val byToken = mapOf(
                "X" to Loss,
                "Y" to Tie,
                "Z" to Win,
            )

            fun from(input: String): Outcome? = byToken[input]

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