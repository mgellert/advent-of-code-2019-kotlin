package org.mgellert.aoc2019.day02

// Problem description: https://adventofcode.com/2019/day/2

private enum class OpCode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    EXIT(99)
}

private tailrec fun run(memory: MutableList<Int>, ip: Int = 0) {
    val opCode = memory[ip]
    when (opCode) {
        OpCode.ADD.code -> {
            memory[memory[ip + 3]] = memory[memory[ip + 1]] + memory[memory[ip + 2]]
            run(memory, ip + 4)
        }

        OpCode.MULTIPLY.code -> {
            memory[memory[ip + 3]] = memory[memory[ip + 1]] * memory[memory[ip + 2]]
            run(memory, ip + 4)
        }

        OpCode.EXIT.code -> return
    }
}

fun runGravityAssistProgram(input: String, noun: Int, verb: Int): Int {
    val memory = input.split(",").map { it.toInt() }.toMutableList()
    memory[1] = noun
    memory[2] = verb
    run(memory)
    return memory[0]
}

fun findParamsForGravityAssistProgram(input: String, target: Int): Int =
    (0..99).flatMap { i -> (0..99).map { j -> i to j } }
        .find { (noun, verb) -> target == runGravityAssistProgram(input, noun, verb) }
        ?.let { (noun, verb) -> 100 * noun + verb } ?: throw IllegalStateException("No solution found")