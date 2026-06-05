import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    private final String file = "num.txt";
    private final Path path = Path.of(file);

    public int read() {
        int result = 0;
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Не удалось создать файл");
            }
            var generator = new UniqueGenerator();
            try {
                Files.writeString(path, String.valueOf(generator.generateUnique()));
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
        return result;
    }

}
