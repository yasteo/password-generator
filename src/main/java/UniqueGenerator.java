import java.util.Random;

public class UniqueGenerator {

    private final int MIN_UNIQUE = 1;
    private final int MAX_UNIQUE = 8193;

    public UniqueGenerator() {}

    public int generateUnique() {
        var random = new Random();
        return Math.abs(random.nextInt(MIN_UNIQUE, MAX_UNIQUE));
    }

}
