import java.io.File


val elvespairs = File("input2.txt").readLinesAs { ElvesPair(it) }


println("Result : ${elvespairs.count { it.isContained() }}")

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

class ElvesPair(input: String) {

    var sections1:IntRange
    var sections2:IntRange

    init {
        val ranges = input.split(",")
        sections1 = ranges[0].split("-")[0].toInt()..ranges[0].split("-")[1].toInt()
        sections2 = ranges[1].split("-")[0].toInt()..ranges[1].split("-")[1].toInt()
    }

    fun isContained() = sections1.any { sections2.contains(it) } || sections2.any { sections1.contains(it) }
}