import java.io.File

//1717
val trees = File("input1.txt").readLinesAs { it.map { c -> c.digitToInt() } }


println("Result : ${countVisibleTrees(trees)}")

fun <T> File.readLinesAs(function: (String) -> T): List<T>  = this.readLines().map { function.invoke(it) }

fun countVisibleTrees(trees: List<List<Int>>): Int {
    var count = 0
  for(y in 0 until trees.size) {
      for(x in 0 until trees[y].size) {
          if(isVisible(trees, x, y)) {
              count++
          }
      }
  }

    return count;
}

class Tree(x: Int, y: Int, size: Int) {

    var leftTree: Tree? = null
    var rightTree: Tree? = null
    var upTree: Tree? = null
    var bottomTree: Tree? = null
}

fun isVisible(trees: List<List<Int>>, x: Int, y: Int): Boolean {
    var size = trees[y][x]

    var leftVisible = true;
    for(left in 0 until x) {
        if(trees[y][left] >= size) {
            leftVisible = false;
            break;
        }
    }

    var rightVisible = true;
    for(right in x + 1 until trees[y].size) {
        if(trees[y][right] >= size) {
            rightVisible = false;
            break;
        }
    }

    var topVisible = true;
    for(top in 0 until y) {
        if(trees[top][x] >= size) {
            topVisible = false;
            break;
        }
    }

    var bottomVisible = true;
    for(bottom in y + 1 until trees.size) {
        if(trees[bottom][x] >= size) {
            bottomVisible = false;
            break;
        }
    }

    return rightVisible || leftVisible || topVisible || bottomVisible;
}