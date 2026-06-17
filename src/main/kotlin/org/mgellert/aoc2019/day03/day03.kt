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

private fun createWire(input: String): Map<Point, Int> {
    val points = mutableMapOf<Point, Int>()

    var lastPoint = Point(0, 0)
    var idx = 0

    val segments = parsePath(input)
    segments.forEach { segment ->
        repeat(segment.distance) {
            val point = Point(lastPoint.x + segment.direction.x, lastPoint.y + segment.direction.y)
            lastPoint = point
            idx += 1
            points.computeIfAbsent(point) { idx }
        }
    }
    return points.toMap()
}

fun findClosestIntersection(input: String): Int {
    val (wire1, wire2) = input.split("\n").map { createWire(it).keys }
    return (wire1 intersect wire2)
        .map { abs(it.x) + abs(it.y) }
        .filter { it != 0 }.min()
}
