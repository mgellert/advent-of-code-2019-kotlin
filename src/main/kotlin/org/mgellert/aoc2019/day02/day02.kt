package org.mgellert.aoc2019.day02

import org.mgellert.aoc2019.intcode.IntcodeComputer
import org.mgellert.aoc2019.intcode.IntcodeComputer.Companion.toMemory


fun runGravityAssistProgram(input: String, noun: Int, verb: Int): Int {
    val memory = input.toMemory()
    memory[1] = noun
    memory[2] = verb
    IntcodeComputer(memory).run()
    return memory[0]
}

fun findParamsForGravityAssistProgram(input: String, target: Int): Int =
    (0..99).flatMap { i -> (0..99).map { j -> i to j } }
        .find { (noun, verb) -> target == runGravityAssistProgram(input, noun, verb) }
        ?.let { (noun, verb) -> 100 * noun + verb } ?: throw IllegalStateException("No solution found")