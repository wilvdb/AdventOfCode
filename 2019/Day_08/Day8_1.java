import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8_1 {

  private static List<String> readLines(String filename) throws Exception {
    URL input = Day8_1.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }

  public static void main(String[] args) throws Exception {
    List<String> input = readLines("day8_1_input.txt");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
    //String input = "123456789012";

    List<List<List<Integer>>> parsed = parse(input.get(0), 25, 6);
    System.out.println("parsed = " + parsed);
    List<List<Integer>> layer = fewest0Layer(parsed);
    System.out.println("layer = " + layer);
    System.out.println("Result 1 = " + (count(layer, 1)));
    System.out.println("Result 2 = " + (count(layer, 2)));
    System.out.println("Result 1 = " + (count(layer, 1) * count(layer, 2)));
  }

  public static List<List<List<Integer>>> parse(String input, int wide, int tall) {
    List<List<List<Integer>>> output = new ArrayList<>();
    for (int l = 0; l < input.length(); ) {
      List<List<Integer>> layer = new ArrayList<>(tall);
      output.add(layer);
      for (int i = 0; i < tall; i++) {
        List<Integer> row = new ArrayList<>(wide);
        layer.add(row);
        for (int j = 0; j < wide; j++) {
          row.add(Integer.valueOf(input.substring(l, l + 1)));
          l++;
        }
      }
    }

    return output;
  }

  public static List<List<Integer>> fewest0Layer(List<List<List<Integer>>> output) {
    int index = 0;
    long counter = Integer.MAX_VALUE;
    for (int i = 0; i < output.size(); i++) {
      if(counter > count(output.get(i), 0)) {
        counter = count(output.get(i), 0);
        index = i;
      }

    }

    return output.get(index);
  }

  public static long count(List<List<Integer>> layer, int digit) {
    return layer.stream().flatMap(row -> row.stream()).filter(el -> el == digit).count();
  }
}
