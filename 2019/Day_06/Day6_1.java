import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_1 {

  private static List<String> readLines(String filename) throws Exception {
    URL input = Day6_1.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }

  public static void main(String[] args) throws Exception {
    List<String> input = readLines("day6_1_input.txt");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "B)G", "G)H", "D)I", "E)J");
    Map<String, List<String>> orbits = new HashMap<>();
    Map<String, String> reverseOrbits = new HashMap<>();
    parse(input, orbits, reverseOrbits);
    System.out.println("countDirect(orbits) = " + input.size());
    System.out.println("countDirect(orbits) = " + countIndirect(orbits, reverseOrbits));
    System.out.println("(input.size() + countIndirect(orbits, reverseOrbits)) = " + (input.size() + countIndirect(orbits, reverseOrbits)));
  }
  
  private static void parse(List<String> input, Map<String, List<String>> orbits, Map<String, String> reverseOrbits) {
    input.forEach(orbit -> add(orbits, orbit, 0, 1));
    input.forEach(orbit -> {
      String[] directOrbit = orbit.split("\\)");
      reverseOrbits.put(directOrbit[1], directOrbit[0]);
    });
  }

  private static void add(Map<String, List<String>> orbits, String orbit, int i, int i2) {
    String[] directOrbit = orbit.split("\\)");
    if (orbits.containsKey(directOrbit[i])) {
      orbits.get(directOrbit[i]).add(directOrbit[i2]);
    } else {
      List<String> orbitList = new ArrayList<>();
      orbitList.add(directOrbit[i2]);
      orbits.put(directOrbit[i], orbitList);
    }
  }

  private static int countIndirect(Map<String, List<String>> orbits, Map<String, String> reverseOrbits) {
    Set<String> startPoints = reverseOrbits.entrySet().stream()
        .filter(entry -> !orbits.containsKey(entry.getKey()))
        .flatMap(entry -> Stream.of(entry.getKey()))
        .collect(Collectors.toSet());

    return multipathLength(reverseOrbits, new HashSet<>(), startPoints);
  }

  private static int multipathLength(Map<String, String> reverseOrbits, Set<String> alreadyProcessed, Set<String> points) {
    int pathLength = points.stream().collect(Collectors.summingInt(point -> pathLength(reverseOrbits, point)));

    if(pathLength == 0) {
      return 0;
    }

    alreadyProcessed.addAll(points);

    Set<String> next = points.stream()
        .map(point -> reverseOrbits.get(point))
        .filter(point -> !"COM".equals(point))
        .filter(point -> !alreadyProcessed.contains(point))
        .collect(Collectors.toSet());

    return pathLength + multipathLength(reverseOrbits, alreadyProcessed, next);
  }

  private static int pathLength(Map<String, String> reverseOrbits, String point) {
    if(!reverseOrbits.containsKey(point)) {
      return -1;
    }

    return pathLength(reverseOrbits, reverseOrbits.get(point)) + 1;
  }
}
