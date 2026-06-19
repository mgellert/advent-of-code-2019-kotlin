package org.mgellert.aoc2019.day04


fun passwordCountWithRule1(input: String): Int {
    return passwordCount(input) { it.contains(0) }
}

fun passwordCountWithRule2(input: String): Int {
    return passwordCount(input) { diffs ->
        (listOf(1) + diffs + listOf(1)).windowed(3).any { it[0] != 0 && it[1] == 0 && it[2] != 0 }
    }
}

private fun passwordCount(input: String, rule: (List<Int>) -> Boolean): Int {
    val (min, max) = parse(input)
    return (min..max).count { n ->
        val diffs = n.toString().map { it.digitToInt() }
            .windowed(2)
            .map { it[1] - it[0] }

        rule(diffs) && diffs.all { it >= 0 }
    }
}

private fun parse(input: String): Pair<Int, Int> {
    val numbers = input.split("-").map { it.toInt() }
    return Pair(numbers[0], numbers[1])
}
