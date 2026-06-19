package org.mgellert.aoc2019.day04

import org.junit.jupiter.api.Test
import org.mgellert.aoc2019.readInput
import kotlin.test.assertEquals

class SecureContainerTest {

    @Test
    fun `should calculate valid passwords for rule one for puzzle input`() {
        assertEquals(2090, passwordCountWithRule1(readInput("day04")))
    }

    @Test
    fun `should calculate valid passwords for rule two for puzzle input`() {
        assertEquals(1419, passwordCountWithRule2(readInput("day04")))
    }

}