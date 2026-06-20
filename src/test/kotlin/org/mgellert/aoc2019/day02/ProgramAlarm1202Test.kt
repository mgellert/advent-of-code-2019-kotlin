package org.mgellert.aoc2019.day02

import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.readInput
import kotlin.test.assertEquals

class ProgramAlarm1202Test {

    @Test
    fun `should run the gravity assist program with set parameters`() {
        val input = readInput("day02")
        val result = runGravityAssistProgram(input, 12, 2)
        assertEquals(3850704, result)
    }

    @Test
    fun `should find correct parameters for the gravity assist program`() {
        val input = readInput("day02")
        val result = findParamsForGravityAssistProgram(input, 19690720)
        assertEquals(6718, result)
    }
}