package dev.patbeagan.days

import java.lang.IllegalArgumentException

/**
 * [Day 11](https://adventofcode.com/2022/day/11)
 */
class Day11 : AdventDay<Int> {
    override fun part1(input: String) = parseInput(input).let { monkeyList ->
        Main(monkeyList, Person()).let { main ->
            repeat(20) {
                main.round()
            }
            monkeyList.forEach {
                println("Monkey ${it.id} inspected items ${it.inspectionCount} times.")
            }
            monkeyList
                .sortedBy { it.inspectionCount }
                .takeLast(2)
                .fold(1) { acc, each -> acc * each.inspectionCount }
                .also { println(it) }
        }
    }

    override fun part2(input: String) = 0

    fun parseInput(input: String) = input
        .trim()
        .split("\n\n")
        .map { Monkey.parse(it) }
        .also { println(it) }

    class Main(
        private val monkeys: List<Monkey>,
        private val person: Person
    ) {
        fun round() {
            monkeys.forEach { monkey ->
                monkey.performTurn(person, monkeys)
            }
            monkeys.forEach {
                println("Monkey ${it.id}: ${it.items}")
            }
        }
    }

    class Person(
        var worryLevel: Int = 0
    ) {
        fun reduceWorryLevel() {
            worryLevel /= 3
        }
    }

    data class Monkey(
        val id: Int,
        val items: MutableList<Item>,
        val operation: Operation,
        val test: MonkeyTest,
        val onTrue: MonkeyAction,
        val onFalse: MonkeyAction,
    ) {
        var inspectionCount = 0
        private fun performItemInspection(item: Item, person: Person, monkeys: List<Monkey>) {
            //
            person.worryLevel = item.worryLevel
            println("  Monkey $id inspects an item with a worry level of ${item.worryLevel}.")
            //
            val oldWorryLevel = person.worryLevel
            person.worryLevel = operation.interpret(person)
            println("    Worry level increases from $oldWorryLevel to ${person.worryLevel}.")
            //
            person.reduceWorryLevel()
            println("    Monkey gets bored with item. Worry level is divided by 3 to ${person.worryLevel}.")
            //
            val divisibleInt = test.asDivisibleInt()
            val worryIsDivisible = person.worryLevel % divisibleInt == 0
            println("    Current worry level is${if (worryIsDivisible) " " else " not"} divisible by ${divisibleInt}.")
            //
            val monkeyAction = if (worryIsDivisible) onTrue else onFalse

            monkeyAction.asThrowActionId()?.let {
                monkeys[it].items.add(Item(person.worryLevel))
            }
            println("    Item with worry level ${item.worryLevel} is thrown to monkey ${monkeyAction.asThrowActionId()}.")
            println()

            inspectionCount++
        }

        fun performTurn(person: Person, monkeys: List<Monkey>) {
            println("Monkey $id.")
            items.forEach { performItemInspection(it, person, monkeys) }
            items.clear()
        }

        @JvmInline
        value class Item(val worryLevel: Int)

        @JvmInline
        value class Operation(private val operationString: String) {
            fun interpret(person: Person): Int = regexOperation
                .find(operationString)
                ?.groupValues!!
                .let { strings ->
                    println("$operationString: $strings")
                    val op = strings[1]
                    val scale = strings[2]
                        .toIntOrNull()
                        ?: when (strings[2]) {
                            "old" -> person.worryLevel
                            else -> throw IllegalArgumentException("implicit var not found")
                        }
                    val new = when (op) {
                        "*" -> person.worryLevel * scale
                        "/" -> person.worryLevel / scale
                        "+" -> person.worryLevel + scale
                        "-" -> person.worryLevel - scale
                        else -> throw IllegalArgumentException()
                    }
                    new
                }

            companion object {
                val regexOperation = Regex("new = old ([^ ]+) ([^ ]*)")
            }
        }

        @JvmInline
        value class MonkeyTest(private val testString: String) {
            fun asDivisibleInt() = regexTest
                .find(testString)
                ?.groupValues
                ?.get(1)
                ?.toInt()!!

            companion object {
                val regexTest = Regex("divisible by (\\d*)")
            }
        }

        @JvmInline
        value class MonkeyAction(private val actionString: String) {
            fun asThrowActionId() = regexThrow
                .find(actionString)
                ?.groupValues
                ?.get(1)
                ?.toIntOrNull()

            companion object {
                val regexThrow = Regex("throw to monkey (\\d*)")
            }
        }

        companion object {
            fun parse(input: String): Monkey = Monkey(
                regexMonkeyId.find(input)
                    ?.groupValues
                    ?.get(1)
                    ?.toInt()!!,
                regexStartingItems.find(input)
                    ?.groupValues
                    ?.get(1)
                    ?.split(",")
                    ?.map { Item(it.trim().toInt()) }
                    ?.toMutableList()!!,
                Operation(
                    regexOperation.find(input)
                        ?.groupValues
                        ?.get(1)!!
                ),
                MonkeyTest(
                    regexTest.find(input)
                        ?.groupValues
                        ?.get(1)!!
                ),
                MonkeyAction(
                    regexOnTrue.find(input)
                        ?.groupValues?.get(1)!!
                ),
                MonkeyAction(
                    regexOnFalse.find(input)
                        ?.groupValues
                        ?.get(1)!!
                ),
            )

            private val regexMonkeyId = Regex("\\w*Monkey (\\d*):")
            private val regexStartingItems = Regex("\\w*Starting items: (.*)")
            private val regexOperation = Regex("\\w*Operation: (.*)")
            private val regexTest = Regex("\\w*Test: (.*)")
            private val regexOnTrue = Regex("\\w*If true: (.*)")
            private val regexOnFalse = Regex("\\w*If false: (.*)")
        }
    }
}