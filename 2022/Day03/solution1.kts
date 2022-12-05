import java.io.File


val rucksacks = File("input1.txt").readLinesAs { Rucksack(it) }


println("Result : ${rucksacks.sumOf { it.getPrioritizedIntersection() }}")

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

class Rucksack(content: String) {

    private var compartment1: List<Char> = content.substring(0 until content.length / 2).map { it }
    private var compartment2: List<Char> = content.substring(content.length / 2 until content.length).map { it }

    private fun getCompartmentIntersection() = compartment1.intersect(compartment2).first()

    fun getPrioritizedIntersection(): Int {
        val intersection = getCompartmentIntersection()
        return if(intersection.isLowerCase()) {
                    intersection.code - 96
                } else {
                    intersection.code - 38
                }
    }

}
