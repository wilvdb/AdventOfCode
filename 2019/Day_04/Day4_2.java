import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Day4_2 {

  public static void main(String[] args) {
    System.out.println("Result = " + IntStream.range(307237, 769058)
        .filter(Day4_2::neverDecrease)
        .filter(Day4_2::adjacent)
        .count());
  }

  private static boolean neverDecrease(int candidate) {
    int previous = digit(candidate, 0);
    for(int i = 1; i < countDigit(candidate); i++) {
      if(previous > digit(candidate, i)) {
        return false;
      }
      previous = digit(candidate, i);
    }

    return true;
  }

  private static boolean adjacent(int candidate) {
    Map<Integer, AtomicInteger> adjacentCounter = new HashMap<>();
    adjacentCounter.put(3, new AtomicInteger(1));
    adjacentCounter.put(4, new AtomicInteger(1));
    adjacentCounter.put(5, new AtomicInteger(1));
    adjacentCounter.put(6, new AtomicInteger(1));
    adjacentCounter.put(7, new AtomicInteger(1));
    adjacentCounter.put(8, new AtomicInteger(1));
    adjacentCounter.put(9, new AtomicInteger(1));
    int previous = digit(candidate, 0);
    for(int i = 1; i < countDigit(candidate); i++) {
      if(previous == digit(candidate, i)) {
        adjacentCounter.get(previous).incrementAndGet();
      }
      previous = digit(candidate, i);
    }

    for (Entry<Integer, AtomicInteger> entry : adjacentCounter.entrySet()) {
      if(entry.getValue().get() == 2) {
        return true;
      }
    }
    return false;
  }

  private static int digit(int candidate, int position) {
    return Integer.valueOf(Integer.toString(candidate).substring(position, position + 1));
  }

  private static int countDigit(int candidate) {
    return Integer.toString(candidate).length();
  }
}
