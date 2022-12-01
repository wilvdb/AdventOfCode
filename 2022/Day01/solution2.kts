import java.io.File

val lines = File("input2.txt").readLinesAsInt()

println("Result : ${aggregate(lines).topN(3)}")

fun aggregate(input: List<Int?>) = input.splitBy { it == null }.map { it.sum() }

fun List<Int?>.splitBy(predicate: (Int?) -> Boolean):List<List<Int>> {
    val result = ArrayList<List<Int>>()
    var currentList = ArrayList<Int>()
    result.add(currentList)
    for (el in this) {
        if(predicate.invoke(el)) {
            currentList = ArrayList()
            result.add(currentList)
            continue
        }

        currentList.add(el?:0)
    }

    return result
}

fun File.readLinesAsInt(): List<Int?>  = this.readLines().map { if (it.isEmpty()) null else it.toInt() }

fun List<Int>.topN(n: Int): Int = this.sorted().reversed().subList(0, n).sum()