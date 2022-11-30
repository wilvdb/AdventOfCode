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

public class Day6_2 {

  private static List<String> readLines(String filename) throws Exception {
    URL input = Day6_2.class.getClassLoader().getResource(filename);
    return Files.readAllLines(Paths.get(input.toURI()));
  }

  public static void main(String[] args) throws Exception {
    List<String> input = readLines("day6_2_input.txt");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L");
    //List<String> input = Arrays.asList("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN");
    Map<String, String> reverseOrbits = new HashMap<>();
    parse(input, reverseOrbits);
    System.out.println("countDirect(orbits) = " + input.size());
    System.out.println("countDirect(orbits) = " + countIndirect(reverseOrbits));
  }

  private static void parse(List<String> input, Map<String, String> reverseOrbits) {
    input.forEach(orbit -> {
      String[] directOrbit = orbit.split("\\)");
      reverseOrbits.put(directOrbit[1], directOrbit[0]);
    });
  }

  private static int countIndirect(Map<String, String> reverseOrbits) {

    Set<String> mypath = new HashSet<>();
    pathLength(reverseOrbits, mypath, "YOU");


    Set<String> sanpath = new HashSet<>();
    pathLength(reverseOrbits, sanpath, "SAN");

    Set<String> intersection = mypath.stream()
        .filter(el -> sanpath.stream()
        .anyMatch(san -> san.equals(el)))
        .collect(Collectors.toSet());

    return pathLength(reverseOrbits, intersection, "YOU") + pathLength(reverseOrbits, intersection, "SAN") - 2;
  }

  private static int pathLength(Map<String, String> reverseOrbits, Set<String> path, String point) {
    if (!reverseOrbits.containsKey(point)) {
      return -1;
    }

    if(path.contains(point)) {
      return 0;
    }

    path.add(point);

    return pathLength(reverseOrbits, path, reverseOrbits.get(point)) + 1;
  }
}
