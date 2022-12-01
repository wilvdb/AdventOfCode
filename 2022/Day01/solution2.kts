import java.io.File

val lines = File("input2.txt").readLinesAsInt()

println("Result : ${sumUpTop(aggregate(lines))}")

fun sumUpTop(input: List<Int>) = input.sorted().reversed().subList(0, 3).sum()


fun aggregate(input: List<Int?>): List<Int> {
    val result = ArrayList<Int>()
    var currentIndex = 0
    result.add(0)
    for (el in input) {
        if(el == null) {
            currentIndex++
            result.add(0)
            continue
        }

        result[currentIndex] += el
    }

    return result
}

fun File.readLinesAsInt(): List<Int?>  = this.readLines().map { if (it.isEmpty()) null else it.toInt() }