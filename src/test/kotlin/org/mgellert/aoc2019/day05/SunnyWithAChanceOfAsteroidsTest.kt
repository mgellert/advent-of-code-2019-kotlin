package org.mgellert.aoc2019.day05

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
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
    fun `should run the diagnostic program for air conditioner` () {
        val input = readInput("day05")
        val computer = IntcodeComputer(input.toMemory())
        computer.run()
        computer.setInput(1)
        computer.run()
        assertEquals(9025675, computer.lastOutput())
    }

    @TestFactory
    fun `should run position mode jump test program with different inputs`(): List<DynamicTest> = listOf(
        -42 to 1,
        0 to 0,
        42 to 1,
    ).map { (input, expected) ->
        dynamicTest("input: $input, output: $expected") {
            val program = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9"
            val computer = IntcodeComputer(program.toMemory())
            computer.run()
            computer.setInput(input)
            assertEquals(Status.EXIT, computer.run())
            assertEquals(expected, computer.lastOutput())
        }
    }

    @TestFactory
    fun `should run immediate mode jump test program with different inputs`(): List<DynamicTest> = listOf(
        -42 to 1,
        0 to 0,
        42 to 1,
    ).map { (input, expected) ->
        dynamicTest("input: $input, output: $expected") {
            val program = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1"
            val computer = IntcodeComputer(program.toMemory())
            computer.run()
            computer.setInput(input)
            assertEquals(Status.EXIT, computer.run())
            assertEquals(expected, computer.lastOutput())
        }
    }

    @Test
    fun `should run the diagnostic program for thermal radiator` () {
        val input = readInput("day05")
        val computer = IntcodeComputer(input.toMemory())
        computer.run()
        computer.setInput(5)
        computer.run()
        assertEquals(11981754, computer.lastOutput())
    }
}