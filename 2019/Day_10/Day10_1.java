import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10_1 {

  final int x, y;
  int count;

  public Day10_1(int x, int y) {
    this.x = x;
    this.y = y;
  }


  @Override
  public boolean equals(Object obj) {
    return x == ((Day10_1) obj).x && y == ((Day10_1) obj).y;
  }

  @Override
  public String toString() {
    return "x = " + x + " y = " + y + " count = " + count;
  }

  private static List<String> readLines(String filename) throws Exception {
    URL input = Day10_1.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }

  public static void main(String[] args) throws Exception {
//    List<String> input = readLines("day10_2_input.txt");
    List<String> input = Arrays.asList(".#..#", ".....", "#####", "....#", "...##");

    List<Day10_1> points = parse(input);
    countAsteroid(points);
    System.out.println("points = " + points);

    int max = points.stream().mapToInt(p -> p.count).max().getAsInt();

    System.out.println("point = " + points.stream().filter(p -> p.count == max).collect(Collectors.toList()));
  }

  private static List<Day10_1> parse(List<String> input) {
    List<Day10_1> output = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      for (int j = 0; j < line.length(); j++) {
        if("#".equals(line.substring(j, j + 1))) {
          output.add(new Day10_1(j, i));
        }
      }
    }

    return output;
  }

  private static void countAsteroid(List<Day10_1> points) {
    for (Day10_1 start : points) {
      for (Day10_1 end : points) {
        if(isVisible(start, end, points)) {
          start.count++;
        }
      }
    }
  }

  private static boolean isVisible(Day10_1 start, Day10_1 end, List<Day10_1> obstacles) {
    if(start.equals(end)) {
      return false;
    }

    if(start.x != end.x) {
      double gradient = (end.y - start.y) / (end.x - start.x);

      return obstacles.stream()
          .filter(point -> !point.equals(start) && !point.equals(end))
              .filter(point -> betweenPoints(start, end, point))
          .noneMatch(point -> ((point.x - start.x) * gradient + start.y) == point.y);
    } else {
      return obstacles.stream()
          .filter(point -> !point.equals(start) && !point.equals(end))
          .noneMatch(point -> betweenPoints(start, end, point));
    }
  }

  private static boolean betweenPoints(Day10_1 point1, Day10_1 point2, Day10_1 thePoint) {
    if(point1.x != thePoint.x) {
      return false;
    }

    if(distance(point1, thePoint) > distance(point1, point2) || distance(point2, thePoint) > distance(point1, point2)) {
      return false;
    }

    if(point1.y < point2.y) {
      return thePoint.y >= point1.y && thePoint.y <= point2.y;
    } else {
      return thePoint.y >= point2.y && thePoint.y <= point1.y;
    }
  }

  private static double distance(Day10_1 point1, Day10_1 point2) {
    return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
  }
}
