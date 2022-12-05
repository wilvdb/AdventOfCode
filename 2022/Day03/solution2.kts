import java.io.File


val groups = File("input2.txt").readLinesAs { Backpack(it) }.windowed(3, 3).map { Group(it) }


println("Result : ${groups.sumOf { it.getPrioritizedIntersection() }}")

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

data class Backpack(val content: String) {

    fun getContent() = content.map { it }

}

class Group(val backpacks: List<Backpack>) {
    private fun getBackpackIntersection() = backpacks[0].getContent().intersect(backpacks[1].getContent().intersect(backpacks[2].getContent())).first()

    fun getPrioritizedIntersection(): Int {
        val intersection = getBackpackIntersection()
        return if(intersection.isLowerCase()) {
            intersection.code - 96
        } else {
            intersection.code - 38
        }
    }

}
