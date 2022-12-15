import java.io.File
import kotlin.math.abs

val motions = File("input1.txt").readLinesAs { Motion(it.split(" ")[0], it.split(" ")[1].toInt()) }

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

var points = listOf<Pair<Int, Int>>(Pair(0, 0))

val rope = Rope()

println("Result : ${motions.flatMap { rope.moveHead(it) }.toSet().size}")
class Motion(val direction: String, val steps: Int)

class Rope {

    var head: Pair<Int, Int> = Pair(10_000, 10_000)
    var tail: Pair<Int, Int> = Pair(10_000, 10_000)

    fun moveHead(motion: Motion): Set<Pair<Int, Int>> {
        var result = setOf<Pair<Int, Int>>(tail)
        for(step in 1..motion.steps) {
            head = when(motion.direction) {
                "R" -> Pair(head.first + 1, head.second)
                "L" -> Pair(head.first - 1, head.second)
                "U" -> Pair(head.first, head.second + 1)
                else -> Pair(head.first, head.second - 1)
            }

            tail = moveTail()
            result += tail
        }

        return result
    }

    private fun moveTail(): Pair<Int, Int> {
        if(needToMove()) {
            if(tail.first == head.first) {
                if(tail.second < head.second) {
                    return Pair<Int, Int>(tail.first, tail.second + 1)
                } else {
                    return Pair<Int, Int>(tail.first, tail.second - 1)
                }
            }

            if(tail.second == head.second) {
                if(tail.first < head.first) {
                    return Pair<Int, Int>(tail.first + 1, tail.second)
                } else {
                    return Pair<Int, Int>(tail.first - 1, tail.second)
                }
            }

            val x = if(tail.first < head.first) { tail.first + 1 } else { tail.first - 1 }
            val y = if(tail.second < head.second) { tail.second + 1 } else { tail.second - 1 }

            return Pair(x, y)
        }

        return tail
    }

    private fun needToMove() = abs(head.first - tail.first) > 1 || abs(head.second - tail.second) > 1
}