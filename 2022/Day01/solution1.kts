import java.io.File

val lines = File("input1.txt").readLinesAsInt()

println("Result : ${aggregate(lines).max()}")

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