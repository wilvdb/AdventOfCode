import java.io.File


val trees = File("input2.txt").readLinesAs { it.map { c -> c.digitToInt() } }


println("Result : ${countVisibleTrees(trees)}")

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

fun countVisibleTrees(trees: List<List<Int>>): Int {
    var visibility = 0
  for(y in 0 until trees.size) {
      for(x in 0 until trees[y].size) {
          val treeVisibility = treeVisibility(trees, x, y)
          if(treeVisibility > visibility) {
              visibility = treeVisibility
          }
      }
  }

    return visibility;
}

fun treeVisibility(trees: List<List<Int>>, x: Int, y: Int): Int {
    var size = trees[y][x]

    var leftVisible = 0
    for(left in x - 1 downTo  0) {
        leftVisible++
        if(trees[y][left] >= size) {
            break
        }
    }

    var rightVisible = 0;
    for(right in x + 1 until trees[y].size) {
        rightVisible++
        if(trees[y][right] >= size) {
            break
        }
    }

    var topVisible = 0;
    for(top in y - 1 downTo  0) {
        topVisible++
        if(trees[top][x] >= size) {
            break
        }
    }

    var bottomVisible = 0;
    for(bottom in y + 1 until trees.size) {
        bottomVisible++
        if(trees[bottom][x] >= size) {
            break
        }
    }

    return rightVisible * leftVisible * topVisible * bottomVisible;
}