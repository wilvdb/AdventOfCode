import java.io.File

val lines = File("input2.txt").readLines()

for(line in lines) {
    println("Result : ${line.detectStartOfPacketMarker(14)}")
}

fun String.detectStartOfPacketMarker(markerSize: Int): Int {
    val windows = this.windowed(markerSize, 1)
    var index = markerSize
    for(window in windows) {
        if(window.hasDuplicate()) {
            index++
        } else {
            return index
        }
    }

    return index
}

fun String.hasDuplicate(): Boolean {
    for(character in this) {
        if(this.containsSeveralTimes(character)) {
            return true
        }
    }

    return false
}

fun String.containsSeveralTimes(character: Char): Boolean {
    var count = 0
    for(c in this) {
        if(c == character) {
            count++
        }
    }

    if(count != 1) {
        return true
    }

    return false
}