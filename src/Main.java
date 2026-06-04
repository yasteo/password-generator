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
                    service = in.readLine();
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                }
            }
            while (true) {
                try {
                    out.write("Введите ваше число: ");
                    out.flush();
                    var input = in.readLine();
                    number = Integer.parseInt(input);
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                } catch (NumberFormatException e) {
                    System.out.println("Указано неправильное число");
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
                    userChoice = (char) in.read();
                    in.readLine();
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода");
                }
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
