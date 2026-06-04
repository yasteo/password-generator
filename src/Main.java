import java.io.*;

public class Main {
    public static void main(String[] args) {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            int number = 0;
            String service = "";
            char userChoice = 'n';
            try {
                out.write("Введите название сервиса: ");
                out.flush();
                service = in.readLine();
            } catch (IOException e) {
                System.err.println("Ошибка ввода");
                System.exit(1);
            }
            try {
                out.write("Введите ваше число: ");
                out.flush();
                var input = in.readLine();
                number = Integer.parseInt(input);
            } catch (IOException e) {
                System.err.println("Ошибка ввода");
                System.exit(1);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат ввода");
                System.exit(1);
            }
            var generator = new Generator(service, number);
            var password = generator.generate();
            try {
                out.write(String.format("Ваш пароль: %s\n", password));
                out.write("Хотите продолжить? [Y/N]: ");
                out.flush();
                userChoice = (char) in.read();
                in.readLine();
                out.flush();
            } catch (IOException e) {
                System.err.println("Неверный формат ввода");
                System.exit(1);
            }
            if (userChoice == 'n' || userChoice == 'N') {
                break;
            }
            if (userChoice != 'y' && userChoice != 'Y') {
                break;
            }
        }
    }
}
