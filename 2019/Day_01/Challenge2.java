import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Challenge2 {

    public static void main(String[] args) throws Exception {
        URL input = Challenge2.class.getClassLoader().getResource("input2.txt");
        System.out.println("Result = " + Files.readAllLines(Path.of(input.toURI())).stream()
                .mapToInt(Integer::valueOf)
                .map(Challenge2::fuel)
                .sum());

    }

    private static int fuel(int mass) {
        var massFuel = massFuel(mass);
        if (massFuel <= 0) {
            return 0;
        } else {
            return massFuel + fuel(massFuel);
        }
    }

    private static int massFuel(int mass) {
        return (mass / 3) - 2;
    }
}