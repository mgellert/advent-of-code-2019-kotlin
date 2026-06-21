package org.mgellert.aoc2019.intcode

import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.IMMEDIATE
import org.mgellert.aoc2019.intcode.IntcodeComputer.ParameterMode.POSITION

typealias Memory = MutableList<Int>


class IntcodeComputer(val memory: Memory) {

    private var ip: Int = 0

    private lateinit var currIns: Instruction

    private val output: MutableList<Int> = mutableListOf()
    private var input: Int? = null

    private enum class OpCode {
        ADD,
        MULTIPLY,
        INPUT,
        OUTPUT,
        JUMP_IF_TRUE,
        JUMP_IF_FALSE,
        LESS_THAN,
        EQUALS,
        EXIT,
    }

    private fun getOpCode(n: Int): OpCode = when (n) {
        1 -> OpCode.ADD
        2 -> OpCode.MULTIPLY
        3 -> OpCode.INPUT
        4 -> OpCode.OUTPUT
        5 -> OpCode.JUMP_IF_TRUE
        6 -> OpCode.JUMP_IF_FALSE
        7 -> OpCode.LESS_THAN
        8 -> OpCode.EQUALS
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

    private fun write(pos: Int, fn: () -> Int) {
        write(pos, fn())
    }

    enum class Status {
        EXIT,
        WAITING
    }

    fun setInput(i: Int) {
        input = i
    }

    fun lastOutput(): Int = output.last()


    fun run(): Status {
        while (true) {
            currIns = memory[ip].toInstruction()
            when (currIns.opCode) {
                OpCode.ADD -> {
                    write(3) { read(1) + read(2) }
                    ip += 4
                }

                OpCode.MULTIPLY -> {
                    write(3) { read(1) * read(2) }
                    ip += 4
                }

                OpCode.INPUT -> {
                    if (input == null) return Status.WAITING
                    write(1) { input!! }
                    input = null
                    ip += 2
                }

                OpCode.OUTPUT -> {
                    val value = read(1)
                    output.add(value)
                    ip += 2
                }

                OpCode.JUMP_IF_TRUE -> {
                    val value = read(1)
                    if (value != 0) ip = read(2) else ip += 3
                }

                OpCode.JUMP_IF_FALSE -> {
                    val value = read(1)
                    if (value == 0) ip = read(2) else ip += 3
                }

                OpCode.LESS_THAN -> {
                    write(3) { if(read(1) < read(2)) 1 else 0 }
                    ip += 4
                }

                OpCode.EQUALS -> {
                    write(3) { if(read(1) == read(2)) 1 else 0 }
                    ip += 4
                }

                OpCode.EXIT -> {
                    return Status.EXIT
                }
            }
        }
    }

    companion object {
        fun String.toMemory() = this.split(",").map { it.toInt() }.toMutableList()
    }
}

