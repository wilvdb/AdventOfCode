val lines = java.io.File("input2.txt").readLines()

val device = Device(lines)

println("Result : ${device.listDirectoryWithAtMost(100000).sumOf { it.size() }}")

data class File(val name: String, val size: Int)

class Directory(val name: String, val parent: Directory? = null) {
    var subDirectories = listOf<Directory>()
    var files = listOf<File>()


    fun addFile(file: File) {
        if(files.find { it.name == name } == null) {
            files += file
        }
    }

    fun addSubDirectory(name: String) {
        if(subDirectory(name) == null) {
            subDirectories += Directory(name, this)
        }
    }

    fun subDirectory(name: String): Directory? =  subDirectories.find { it.name == name }

    fun size():Int = files.sumOf { it.size } + subDirectories.sumOf { dir -> dir.size() }

    fun hasSizeAtMost(limit: Int): Boolean = size() <= limit

    fun flattenSubDirectories(): List<Directory> = subDirectories + subDirectories.flatMap { it.flattenSubDirectories() }

}

class Device(input: List<String>) {
    val rootDirectory: Directory = Directory("/")

    init {
        var currentDirectory = rootDirectory
        for(line in input) {
            if(line.startsWith("$ cd")) {
                val directoryName = line.removePrefix("$ cd").trim()
                currentDirectory = when(directoryName) {
                    ".." -> currentDirectory.parent!!
                    "/" -> rootDirectory
                    else -> currentDirectory.subDirectory(directoryName)!!
                }
            } else if(line.startsWith("dir")) {
                val directoryName = line.removePrefix("dir").trim()
                currentDirectory.addSubDirectory(directoryName)
            } else if(line.startsWith("$ ls")) {
                // ignore
            } else {
                val fileDescriptor = line.split(" ")
                currentDirectory.addFile(File(fileDescriptor[1], fileDescriptor[0].toInt()))
            }
        }
    }

    fun listDirectoryWithAtMost(limit: Int): List<Directory> = rootDirectory.flattenSubDirectories().filter { it.hasSizeAtMost(limit) }
}