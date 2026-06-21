package org.mgellert.aoc2019.day05

import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.intcode.IntcodeComputer
import org.mgellert.aoc2019.intcode.IntcodeComputer.Companion.toMemory
import org.mgellert.aoc2019.intcode.IntcodeComputer.Status
import org.mgellert.aoc2019.readInput
import kotlin.test.assertEquals

class SunnyWithAChanceOfAsteroidsTest {

    @Test
    fun `should output what is input`() {
        val input = "3,0,4,0,99"
        val computer = IntcodeComputer(input.toMemory())
        assertEquals(Status.WAITING, computer.run())
        computer.setInput(42)
        assertEquals(Status.EXIT, computer.run())
        assertEquals(42, computer.lastOutput())
    }

    @Test
    fun `should run the diagnostic program` () {
        val input = readInput("day05")
        val computer = IntcodeComputer(input.toMemory())
        computer.run()
        computer.setInput(1)
        computer.run()
        assertEquals(9025675, computer.lastOutput())
    }
}