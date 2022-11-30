import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge1 {

  public static void main(String[] args) throws Exception {
    List<Integer> input = readLines("input1.txt").stream()
        .flatMap(Challenge1::split)
        .collect(Collectors.toList());

    System.out.println("input = " + compute(0, input));
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
    URL input = Challenge1.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }
}
