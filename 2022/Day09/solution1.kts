import java.io.File
import kotlin.math.abs

typealias Knot = Pair<Int, Int>

val motions = File("input1.txt").readLinesAs { Motion(it.split(" ")[0], it.split(" ")[1].toInt()) }

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }


val rope = Rope()

println("Result : ${motions.flatMap { rope.moveHead(it) }.toSet().size}")
class Motion(val direction: String, val steps: Int)

class Rope {

    var head: Knot = Knot(10_000, 10_000)
    var tail: Knot = Knot(10_000, 10_000)

    fun moveHead(motion: Motion): Set<Knot> {
        var result = setOf(tail)
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

    private fun moveTail(): Knot {
        if(head.needToMove(tail)) {
            if(tail.first == head.first) {
                return if(tail.second < head.second) {
                    tail.copy(second = tail.second + 1)
                } else {
                    tail.copy(second = tail.second - 1)
                }
            }

            if(tail.second == head.second) {
                return if(tail.first < head.first) {
                    tail.copy(first = tail.first + 1)
                } else {
                    tail.copy(first = tail.first - 1, tail.second)
                }
            }

            val x = if(tail.first < head.first) { tail.first + 1 } else { tail.first - 1 }
            val y = if(tail.second < head.second) { tail.second + 1 } else { tail.second - 1 }

            return Knot(x, y)
        }

        return tail
    }

}

fun Knot.needToMove(knot: Knot) = abs(this.first - knot.first) > 1 || abs(this.second - knot.second) > 1