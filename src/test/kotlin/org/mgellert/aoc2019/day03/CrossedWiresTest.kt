package org.mgellert.aoc2019.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.readInput

class CrossedWiresTest {

    @Test
    fun `should calculate distance of closes cross to origo for test input`() {
        val input = """
            R8,U5,L5,D3
            U7,R6,D4,L4
        """.trimIndent()
        assertEquals(6, findClosestIntersection(input))
    }

    @Test
    fun `should calculate distance of closes cross to origo for puzzle input`() {
        val input = readInput("day03")
        assertEquals(3229, findClosestIntersection(input))
    }

    @Test
    fun `should find the fewest combined steps the wires must take to reach an intersection for test input`() {
        val input = """
            R8,U5,L5,D3
            U7,R6,D4,L4
        """.trimIndent()
        assertEquals(30, findFewestCombinedSteps(input))
    }

    @Test
    fun `should find the fewest combined steps the wires must take to reach an intersection for puzzle input`() {
        val input = readInput("day03")
        assertEquals(32132, findFewestCombinedSteps(input))
    }

}