package org.mgellert.aoc2019.day01

// Problem description: https://adventofcode.com/2019/day/1

private fun fuelRequirement(module: Long): Long = module / 3 - 2

fun fuelRequirements(input: String): Long =
    input.trim().lines().map { it.toLong() }.sumOf(::fuelRequirement)