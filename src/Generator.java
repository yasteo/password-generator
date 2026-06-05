import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Generator {

    private final String serviceName;
    private final int serviceLength;
    private final int number;
    private static final int unique;
    private final int PASSWORD_LENGTH = 16;
    private static final int MIN_UNIQUE = 1;
    private static final int MAX_UNIQUE = 8193;

    static {
        var result = 0;
        var path = Path.of("num.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Не удалось создать файл");
            }
            var random = new Random();
            try {
                Files.writeString(path, String.valueOf(random.nextInt(MIN_UNIQUE, MAX_UNIQUE)));
            } catch (IOException e) {
                System.out.println("Не удалось напечать число в файл");
            }
        }
        try {
            var text = Files.readAllLines(path);
            result = Integer.parseInt(text.getFirst());
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа в файле");
        }
        unique = result;
    }

    public Generator(String serviceName, int number) {
        this.serviceName = serviceName;
        this.serviceLength = serviceName.length();
        this.number = number;
    }

    public String generate() {
        String allowedChars = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*-_+=";
        int len = allowedChars.length();
        var builder = new StringBuilder();
        for (int i = 0; i < PASSWORD_LENGTH; ++i) {
            int idx = calculateIndex(serviceName.charAt(i % serviceLength), i + 1, len);
            builder.append(allowedChars.charAt(idx));
        }
        return builder.toString();
    }

    private int calculateIndex(char c, int x, int len) {
        long idx = (long) this.number * x   * x * x + (long) this.serviceLength * x * x + (long) unique * x + (long) c;
        return (int) Math.floorMod(idx, len);
    }
}
