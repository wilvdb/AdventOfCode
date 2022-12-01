import java.io.File

val lines = File("input1.txt").readLinesAsInt()

println("Result : ${lines}")


fun File.readLinesAsInt(): List<Int?>  = this.readLines().map { if (it.isEmpty()) null else it.toInt() }