import java.io.File

val instructions = File("input1.txt").readLinesAs { Instruction.of(it) }

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

val xValues = mutableListOf<Int>(1)

instructions.forEach {
    if(it.cycle == 2) {
        xValues += xValues.last()
    }

    xValues += it.execute(xValues.last())
}

println("Result : ${xValues[19] * 20 + xValues[59] * 60 + xValues[99] * 100 + xValues[139] * 140 + xValues[179] * 180 + xValues[219] * 220}")

sealed class Instruction(val cycle: Int, val value: Int) {
    companion object {

        fun of(input: String): Instruction {
            return when(input) {
                "noop" -> Noop()
                else -> Addx(input.split(" ")[1].toInt())
            }
        }
    }

    abstract fun execute(x: Int): Int
}

class Noop: Instruction(1, 0) {
    override fun execute(x: Int): Int = x
}

class Addx(value: Int): Instruction(2, value) {
    override fun execute(x: Int): Int = x + this.value
}
