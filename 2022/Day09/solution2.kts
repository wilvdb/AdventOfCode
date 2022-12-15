import java.io.File
import kotlin.math.abs

val motions = File("input2.txt").readLinesAs { Motion(it.split(" ")[0], it.split(" ")[1].toInt()) }

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

val rope = Rope()

println("Result : ${motions.flatMap { rope.move(it) }.toSet().size}")

data class Motion(val direction: String, val steps: Int)

data class Knot(val x: Int = 10_000, val y: Int = 10_000)

class Rope {

    private val knots = mutableListOf(
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
        Knot(),
    )

    fun move(motion: Motion): Set<Knot> {
        val result = mutableSetOf(knots.last())
        for(step in 1..motion.steps) {

            val knot = knots[0]
            knots[0] = when(motion.direction) {
                "R" -> knot.copy(x = knot.x + 1)
                "L" -> knot.copy(x = knot.x - 1)
                "U" -> knot.copy(y = knot.y + 1)
                else -> knot.copy(y = knot.y - 1)
            }

            for(knotIndex in 1 until knots.size) {
                knots[knotIndex] = moveNext(knots[knotIndex - 1], knots[knotIndex])

                if(knotIndex == knots.size - 1) {
                    result += knots[knotIndex]
                }
            }
        }

        return result
    }

    private fun moveNext(current: Knot, next: Knot): Knot {
        if(needToMove(current, next)) {
            if(next.x == current.x) {
                return if(next.y < current.y) {
                    next.copy(y = next.y + 1)
                } else {
                    next.copy(y = next.y - 1)
                }
            }

            if(next.y == current.y) {
                return if(next.x < current.x) {
                    next.copy(x = next.x + 1)
                } else {
                    next.copy(x = next.x - 1)
                }
            }

            val x = if(next.x < current.x) { next.x + 1 } else { next.x - 1 }
            val y = if(next.y < current.y) { next.y + 1 } else { next.y - 1 }

            return Knot(x, y)
        }

        return next
    }

    private fun needToMove(current: Knot, next: Knot) = abs(current.x - next.x) > 1 || abs(current.y - next.y) > 1
}