package org.mgellert.aoc2019.intcode


private enum class OpCode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    EXIT(99)
}

tailrec fun run(memory: MutableList<Int>, ip: Int = 0) {
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
