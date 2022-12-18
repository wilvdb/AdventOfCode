import java.io.File

val instructions = File("input2.txt").readLinesAs { Instruction.of(it) }

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

val xValues = mutableListOf(1)

instructions.forEach {
    if(it.cycle == 2) {
        xValues += xValues.last()
    }

    xValues += it.execute(xValues.last())
}

for(cycle in 0 until xValues.size - 1 step 40) {
    for(i in 0..39) {
        if(xValues[cycle + i] <= i + 1 && xValues[cycle + i] >= i - 1) {
            print("#")
        } else {
            print(".")
        }
    }
    println()
}

// REHPRLUB
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
