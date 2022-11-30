import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge2 {

  public static void main(String[] args) throws Exception {
    List<Integer> input = readLines("input2.txt").stream()
        .flatMap(Challenge2::split)
        .collect(Collectors.toList());

    List<Integer> result = lookup(input);
    System.out.println("input = " + result);
    System.out.println("result = " + (100 * result.get(1) + result.get(2)));
  }

  private static List<Integer> lookup(List<Integer> initial) {
    int value1 = 0;
    int value2 = 0;
    while (true) {
      while (true) {
        List<Integer> copyAndReplaced = copyAndReplace(value1, value2, initial);
        int result = compute(new ArrayList<>(copyAndReplaced));
        if(result == 19690720) {
          return copyAndReplaced;
        }

        if (value2 == initial.size() - 2) {
          value2 = 0;
          break;

        }
        value2++;
      }
      value1++;
    }
  }

  private static List<Integer> copyAndReplace(int value1, int value2, List<Integer> input) {
    System.out.println("value1 = " + value1 + ", value2 = " + value2 + ", input = " + input);
    List<Integer> output = new ArrayList<>(input);
    output.set(1, value1);
    output.set(2, value2);

    return output;
  }

  private static int compute(List<Integer> input) {
    List<Integer> computed = compute(0, input);
    System.out.println("computed = " + computed);
    return computed.get(0);
  }

  private static List<Integer> compute(int index, List<Integer> input) {
    if (index > input.size()) {
      return input;
    }

    int opCode = input.get(index);
    int value = 0;
    switch (opCode) {
      case 1:
        value = input.get(input.get(index + 1)) + input.get(input.get(index + 2));
        break;
      case 2:
        value = input.get(input.get(index + 1)) * input.get(input.get(index + 2));
        break;
      case 99:
        return input;
    }
    int overwrite = input.get(index + 3);
    input.set(overwrite, value);
    return compute(index + 4, input);
  }

  private static Stream<Integer> split(String input) {
    return Stream.of(input.split(","))
        .map(Integer::valueOf);
  }

  private static List<String> readLines(String filename) throws Exception {
    URL input = Challenge2.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }
}
