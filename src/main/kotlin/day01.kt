package org.mgellert.aoc2019.day01

// Problem description: https://adventofcode.com/2019/day/1

private fun fuelRequirement(module: Long): Long = module / 3 - 2

private tailrec fun recursiveFuelRequirement(module: Long, acc: Long = 0): Long {
    val fuel = fuelRequirement(module)
    if (fuel <= 0) return acc
    return recursiveFuelRequirement(fuel, acc + fuel)
}

private fun parseInput(input: String) = input.trim().lines().map { it.toLong() }

fun fuelRequirements(input: String): Long =
    parseInput(input).sumOf(::fuelRequirement)

fun recursiveFuelRequirements(input: String): Long =
    parseInput(input).sumOf(::recursiveFuelRequirement)