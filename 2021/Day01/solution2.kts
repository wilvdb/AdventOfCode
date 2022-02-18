import java.io.File

val lines = File("input2.txt").readLinesAsInt()

val values = lines.windowed(3, 1, false) { it.sum() }

println("Result : ${count(values)}")

fun count(values: List<Int>): Int = values.windowed(2, 1, false)
    .count { it.first() < it.last() }

fun File.readLinesAsInt(): List<Int>  = this.readLines().map { it.toInt() }