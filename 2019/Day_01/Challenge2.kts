import java.io.File

fun fuel(mass:Int):Int {
    val massFuel = (mass / 3) - 2
    return when (massFuel <= 0) {
        true -> 0
        false -> massFuel + fuel(massFuel)
    }
}

val result = File("input2.txt").readLines()
        .map { it.toInt() }
        .map { fuel(it) }
        .sum()

println("Result = $result")