import java.io.File

val result = File("input1.txt").readLines()
    .map { it.toInt() }
    .map { (it / 3) - 2 }
    .sum()

println("Result = $result")