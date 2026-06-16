package org.mgellert.aoc2019

import java.io.File

fun readInput(day: String): String = File("input/$day.txt").readText().trim()