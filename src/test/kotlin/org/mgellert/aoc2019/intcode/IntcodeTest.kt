package org.mgellert.aoc2019.intcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.intcode.IntcodeComputer.Companion.toMemory

class IntcodeTest {

    @Test
    fun `test 1`() {
        val program = "1,9,10,3,2,3,11,0,99,30,40,50"
        val computer = IntcodeComputer(program.toMemory())
        computer.run()
        assertEquals(3500, computer.memory[0])
        assertEquals(70, computer.memory[3])
    }

    @Test
    fun `test 2`() {
        val program = "2,4,4,5,99,0"
        val computer = IntcodeComputer(program.toMemory())
        computer.run()
        assertEquals(9801, computer.memory[5])
    }

}