package org.mgellert.aoc2019.day06

private fun parse(input: String): Map<String, List<String>> {
    return input.lines().map { line ->
        val (left, right) = line.split(")")
        left to right
    }.groupBy({ it.first }, { it.second })
}

fun sumDirectAndIndirectOrbits(input: String): Int {
    val map = parse(input)

    val stack = ArrayDeque(map["COM"]!!.map { it to "COM" })
    val count = mutableMapOf<String, Int>()

    while (stack.isNotEmpty()) {
        val (node, parent) = stack.removeFirst()
        count[node] = count.getOrDefault(parent, 0) + 1

        map[node]?.let { children ->
            stack.addAll(children.map { it to node })
        }
    }

    return count.values.sum()
}