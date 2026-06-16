package org.mgellert.aoc2019.day03

import kotlin.math.abs

val segmentRegex = Regex("""([RLUD])(\d+)""")

private enum class Direction(val x: Int, val y: Int) {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    companion object {
        fun fromString(str: String): Direction {
            return when (str) {
                "U" -> UP
                "D" -> DOWN
                "L" -> LEFT
                "R" -> RIGHT
                else -> throw IllegalArgumentException("Unknown direction: $str")
            }
        }
    }
}

private data class Segment(val direction: Direction, val distance: Int)

private data class Point(val x: Int, val y: Int)

private fun parsePath(input: String): List<Segment> = input.split(",")
    .mapNotNull {
        segmentRegex.matchEntire(it)?.let { match ->
            val (direction, distance) = match.destructured
            Segment(Direction.fromString(direction), distance.toInt())
        }
    }

fun findClosestIntersection(input: String): Int {
    return input.split("\n").map { line ->
        val points = mutableListOf<Point>()
        points.add(Point(0, 0))
        val segments = parsePath(line)
        segments.forEach { segment ->
            repeat(segment.distance) {
                val p = points.last()
                points.add(Point(p.x + segment.direction.x, p.y + segment.direction.y))
            }
        }
        points.toSet()
    }.reduceOrNull { acc, s -> acc intersect s }
        ?.filter { it != Point(0, 0) }
        ?.minOf { p -> abs(p.x) + abs(p.y) }
        ?: throw IllegalStateException("Wires do not cross")
}
