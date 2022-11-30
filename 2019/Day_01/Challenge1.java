import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Challenge1 {

    public static void main(String[] args) throws Exception {
        URL input = Challenge1.class.getClassLoader().getResource("input1.txt");
        System.out.println("Result = " + Files.readAllLines(Path.of(input.toURI())).stream()
                .mapToInt(Integer::valueOf)
                .map(Challenge1::fuel)
                .sum());

    }

    private static int fuel(int mass) {
        return (mass / 3) - 2;
    }
}