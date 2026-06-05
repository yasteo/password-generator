import java.io.*;

public class Main {
    public static void main(String[] args) {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            int number = 0;
            String service = "";
            char userChoice = 'q';
            while (true) {
                try {
                    out.write("Введите название сервиса: ");
                    out.flush();
                    service = in.readLine().trim();
                    if (service.isBlank()) {
                        throw new IllegalStateException("Пустое название сервиса");
                    }
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true) {
                try {
                    out.write("Введите ваше число: ");
                    out.flush();
                    var input = in.readLine().trim();
                    number = Integer.parseInt(input);
                    if (number < 0) {
                        throw new IllegalStateException("Число должно быть больше нуля");
                    }
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                } catch (NumberFormatException e) {
                    System.out.printf("Число должно быть меньше, чем %d\n", Integer.MAX_VALUE);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            var generator = new Generator(service, number);
            var password = generator.generate();
            try {
                out.write(String.format("Ваш пароль: %s\n", password));
                out.flush();
            } catch (IOException e) {
                System.out.println("Ошибка вывода");
            }
            while (userChoice != 'n' && userChoice != 'N' && userChoice != 'y' && userChoice != 'Y') {
                try {
                    out.write("Хотите продолжить? [Y/N]: ");
                    out.flush();
                    userChoice = in.readLine().trim().charAt(0);
                    in.readLine();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                }
            }
            if (userChoice == 'n' || userChoice == 'N') {
                break;
            }
        }
    }
}
