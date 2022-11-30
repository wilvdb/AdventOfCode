import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8_2 {

  private static List<String> readLines(String filename) throws Exception {
    URL input = Day8_2.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }

  public static void main(String[] args) throws Exception {
    List<String> input = readLines("day8_2_input.txt");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
    //String input = "123456789012";

    List<List<List<Integer>>> parsed = parse(input.get(0), 25, 6);
    System.out.println("parsed = " + parsed);
//    System.out.println("merge(parsed) = " + merge(parsed, 25, 6));
    print(merge(parsed, 25, 6));
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

  public static List<List<Integer>> merge(List<List<List<Integer>>> input, int wide, int tall) {
    List<List<Integer>> result = new ArrayList<>(input.get(0));
    for (int i = 0; i < input.size(); i++) {
      for (int w = 0; w < wide; w++) {
        for (int t = 0; t < tall; t++) {
          if(result.get(t).get(w) == 2) {
            result.get(t).set(w, input.get(i).get(t).get(w));
          }
        }
      }
    }

    return result;
  }

  private static void print(List<List<Integer>> input) {
    input.forEach(row -> System.out.println(row));
  }

}
