import java.io.File

val lines = File("input1.txt").readLinesAsInt()

println("Result : ${count(lines)}")

fun count(values: List<Int>): Int = values.windowed(2, 1, false)
    .count { it.first() < it.last() }

fun File.readLinesAsInt(): List<Int>  = this.readLines().map { it.toInt() }