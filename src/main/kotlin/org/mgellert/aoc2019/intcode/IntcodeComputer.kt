package org.mgellert.aoc2019.intcode

import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.IMMEDIATE
import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.POSITION

typealias Memory = MutableList<Int>


class IntcodeComputer(val memory: Memory, private var ip: Int = 0) {

    private lateinit var currIns: Instruction

    private enum class OpCode(val inc: Int) {
        ADD(4),
        MULTIPLY(4),
        EXIT(0),
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

    private fun read(pos: Int): Int {
        val mode = currIns.modes[pos - 1]
        return when (mode) {
            POSITION -> memory[memory[ip + pos]]
            IMMEDIATE -> memory[ip + pos]
        }
    }

    private fun write(pos: Int, value: Int) {
        memory[memory[ip + pos]] = value
    }

    tailrec fun run() {
        currIns = memory[ip].toInstruction()
        when (currIns.opCode) {
            OpCode.ADD -> write(3) { read(1) + read(2) }
            OpCode.MULTIPLY -> write(3) { read(1) * read(2) }
            OpCode.EXIT -> return
        }
        ip += currIns.opCode.inc
        run()
    }

    private fun write(pos: Int, fn: () -> Int) {
        write(pos, fn())
    }

    companion object {
        fun String.toMemory() = this.split(",").map { it.toInt() }.toMutableList()
    }
}

