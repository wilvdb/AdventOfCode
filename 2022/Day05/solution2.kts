import java.io.File

val crane = Crane()
crane.arrange()

println("Result : ${crane.supplies.topOfStacks()}")

class Crane {

    private var instructions: List<Instruction>
    var supplies: Supplies

    init {
        val parsingResult = parseInput()
        instructions = parsingResult.second.map { Instruction(it) }
        supplies = Supplies(parsingResult.first)
    }

    private fun parseInput(): Pair<List<String>, List<String>> {
        val input = File("input2.txt").readLines()
        val separatorIndex = input.indexOf("")

        return Pair(input.subList(0, separatorIndex), input.subList(separatorIndex + 1, input.size))
    }

    fun arrange() {
        instructions.forEach { supplies.move(it) }
    }

    override fun toString(): String {
        return "$supplies - $instructions"
    }
}
class Stack(val position: Int) {
    var crates: List<Char> = ArrayList()

    fun addCrate(input: String) {
        if(input.length > position && input[position] != ' ') {
            addCrate(input[position])
        }
    }

    fun addCrate(name: Char) {
        crates += name
    }

    fun removeCrates(count: Int): List<Char> {
        val result = crates.subList(crates.size - count, crates.size)
        crates = crates.subList(0, crates.size - count)

        return result;
    }

    override fun toString(): String {
        return crates.joinToString(" ", "[", "]")
    }

}

class Supplies(input: List<String>) {
    var stacks: List<Stack> = listOf()

    init {
        input.last().forEachIndexed { index, value -> if(value.isDigit()) stacks += Stack(index) }

        input.reversed().forEach { value -> stacks.forEach { it.addCrate(value) } }
    }

    fun topOfStacks() = stacks.joinToString("") { it.crates.last().toString() }

    override fun toString(): String {
        return stacks.joinToString("\n") { it.toString() }
    }

    fun move(instruction: Instruction) {
        val crates = stacks[instruction.from - 1].removeCrates(instruction.count)
        crates.forEach { stacks[instruction.to - 1].addCrate(it) }
    }
}

class Instruction(val input: String) {
    var count: Int
    var from: Int
    var to: Int

    init {
        val result = Regex("""^move (\d*) from (\d*) to (\d*)$""").find(input)
        count = result?.groups?.get(1)?.value?.toInt()!!
        from = result.groups[2]?.value?.toInt()!!
        to = result.groups[3]?.value?.toInt()!!
    }

    override fun toString(): String {
        return "move $count from $from to $to"
    }
}