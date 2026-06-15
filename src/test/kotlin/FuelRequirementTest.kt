package org.mgellert.aoc2019.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.readInput

class FuelRequirementTest {

    @Test
    fun `should sum fuel requirements of test input`() {
        assertEquals(34241, fuelRequirements(testInput))
    }

    @Test
    fun `should sum fuel requirements of puzzle input`() {
        assertEquals(3404722, fuelRequirements(input))
    }

    @Test
    fun `should sum recursive fuel requirements of test input`() {
        assertEquals(51316, recursiveFuelRequirements(testInput))
    }

    @Test
    fun `should sum recursive fuel requirements of puzzle input`() {
        assertEquals(5104215, recursiveFuelRequirements(input))
    }

    companion object {
        private val testInput = """
            12
            14
            1969
            100756
        """.trimIndent() // 34241

        private val input = readInput("day01")
    }
}