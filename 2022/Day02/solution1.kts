import java.io.File


val challenge = File("input1.txt").readLinesAsInt()


println("Result : ${challenge.sumOf { it.getPoints() }}")

fun File.readLinesAsInt(): List<Round>  = this.readLines().map { Round(it.substring(0, 1), it.substring(2, 3)) }

class Round(val elve: String, val me: String) {

    private fun getElvePoints() = getPoints(elve)

    private fun getMyPoints() = getPoints(me)
    fun getPoints() = when {
        isDraw()-> 3 + getMyPoints()
        isWin() -> 6 + getMyPoints()
        else -> getMyPoints()
    }

    private fun getPoints(letter: String) = when(letter) {
        "A" -> 1
        "X" -> 1
        "B" -> 2
        "Y" -> 2
        "C" -> 3
        "Z" -> 3
        else -> 0
    }

    private fun isWin() = when {
        getElvePoints() == 1 && getMyPoints() == 2 -> true
        getElvePoints() == 2 && getMyPoints() == 3 -> true
        getElvePoints() == 3 && getMyPoints() == 1 -> true
        else -> false
    }

    private fun isDraw() = getElvePoints() == getMyPoints()
}