package org.mgellert.aoc2019.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.readInput

class UniversalOrbitMapTest {


    @Test
    fun `should sum orbits for test input`() {
        val input = """
            COM)B
            B)C
            C)D
            D)E
            E)F
            B)G
            G)H
            D)I
            E)J
            J)K
            K)L
        """.trimIndent()
        assertEquals(42, sumDirectAndIndirectOrbits(input))
    }

    @Test
    fun `should sum orbits for puzzle input`() {
        val input = readInput("day06")
        assertEquals(200001, sumDirectAndIndirectOrbits(input))
    }

}