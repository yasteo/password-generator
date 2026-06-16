import java.util.Random;

public class UniqueGenerator {

    public UniqueGenerator() {}

    public int generateUnique() {
        var random = new Random();
        return Math.abs(random.nextInt(1, 8193));
    }

}
