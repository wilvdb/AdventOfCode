import java.io.File


val challenge = File("input2.txt").readLinesAsInt()


println("Result : ${challenge.sumOf { it.getPoints() }}")

fun File.readLinesAsInt(): List<Round>  = this.readLines().map { Round(it.substring(0, 1), it.substring(2, 3)) }

class Round(val elve: String, val me: String) {

    private fun getElvePoints() =  when(elve) {
        "A" -> 1
        "B" -> 2
        "C" -> 3
        else -> 0
    }

    fun getPoints() = when {
        isDraw() -> 3 + getElvePoints()
        isWin() -> 6 + if (getElvePoints() + 1 == 4)  1 else getElvePoints() + 1
        else -> if (getElvePoints() - 1 == 0)  3 else getElvePoints() - 1
    }

    private fun isWin() = me == "Z"

    private fun isDraw() = me == "Y"
}