package org.mgellert.aoc2019.intcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.intcode.IntcodeComputer.toMemory

class IntcodeTest {

    @Test
    fun `test 1`() {
        val program = "1,9,10,3,2,3,11,0,99,30,40,50"
        val memory = program.toMemory()
        IntcodeComputer.run(memory)
        assertEquals(3500, memory[0])
        assertEquals(70, memory[3])
    }

    @Test
    fun `test 2`() {
        val program = "2,4,4,5,99,0"
        val memory = program.toMemory()
        IntcodeComputer.run(memory)
        assertEquals(9801, memory[5])
    }

}