import java.util.stream.IntStream;

public class Day4_1 {

  public static void main(String[] args) {
    System.out.println("Result = " + IntStream.range(307237, 769058)
        .filter(Day4_1::neverDecrease)
        .filter(Day4_1::adjacent)
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
    int previous = digit(candidate, 0);
    for(int i = 1; i < countDigit(candidate); i++) {
      if(previous == digit(candidate, i)) {
        return true;
      }
      previous = digit(candidate, i);
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
