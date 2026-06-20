package org.mgellert.aoc2019.intcode

import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.IMMEDIATE
import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.POSITION

typealias Memory = MutableList<Int>


object IntcodeComputer {
    private enum class OpCode {
        ADD,
        MULTIPLY,
        EXIT,
    }

    private fun getOpCode(n: Int): OpCode = when (n) {
        1 -> OpCode.ADD
        2 -> OpCode.MULTIPLY
        99 -> OpCode.EXIT
        else -> throw IllegalArgumentException("No such opcode: $n")
    }

    private enum class ParameterMode {
        POSITION,
        IMMEDIATE,
    }

    private fun getParameterMode(n: Int): ParameterMode = when (n) {
        0 -> POSITION
        1 -> IMMEDIATE
        else -> throw IllegalArgumentException("No such parameter mode: $n")
    }

    private data class Instruction(
        val opCode: OpCode,
        val modes: List<ParameterMode>,
    )

    private fun Int.toInstruction(): Instruction {
        return Instruction(
            getOpCode(this % 100),
            listOf(
                getParameterMode((this / 100) % 10),
                getParameterMode((this / 1000) % 10),
                getParameterMode((this / 10000) % 10),
            )
        )
    }

    private fun Memory.read(pos: Int, ins: Instruction, ip: Int): Int {
        val mode = ins.modes[pos - 1]
        return when (mode) {
            POSITION -> this[this[ip + pos]]
            IMMEDIATE -> this[ip + pos]
        }
    }

    private fun Memory.write(pos: Int, value: Int, ip: Int) {
        this[this[ip + pos]] = value
    }

    fun String.toMemory() = this.split(",").map { it.toInt() }.toMutableList()

    tailrec fun run(memory: Memory, ip: Int = 0) {
        val instruction = memory[ip].toInstruction()
        when (instruction.opCode) {
            OpCode.ADD -> {
                val value = memory.read(1, instruction, ip) + memory.read(2, instruction, ip)
                memory.write(3, value, ip)
                run(memory, ip + 4)
            }

            OpCode.MULTIPLY -> {
                memory[memory[ip + 3]] = memory[memory[ip + 1]] * memory[memory[ip + 2]]
                run(memory, ip + 4)
            }

            OpCode.EXIT -> return
        }
    }
}

