import java.io.*;
import java.sql.SQLException;

public class Main {

    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        var worker = new DatabaseWorker();
        try {
            worker.connect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        in = new BufferedReader(new InputStreamReader(System.in));
        out = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                showMenu();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            var userChoice = letUserChoose();
            switch (userChoice) {
                case 1 -> {
                    var service = getServiceName();
                    var number = getNumber();
                    var password = generate(service, number);
                    try {
                        out.write(String.format("Ваш пароль: %s\n", password));
                        out.flush();
                        worker.insert(service, number);
                    } catch (IOException | SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        var result = worker.getAll();
                        out.write(result);
                        out.flush();
                    } catch (IOException | SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    var service = getServiceName();
                    try {
                        var result = worker.getNumberByServiceName(service);
                        out.write(result);
                        out.flush();
                    } catch (IOException | SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (userChoice == 0) break;
        }
        try {
            in.close();
            out.close();
            worker.disconnect();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showMenu() throws IOException {
        String builder = """
                [1] - Создать новый пароль
                [2] - Вывести данные для паролей
                [3] - Поиск числа по названию
                [0] - Выход
                """;
        out.write(builder);
        out.flush();
    }

    public static int letUserChoose() {
        while (true) {
            try {
                out.write("Ваш выбор: ");
                out.flush();
                var input = in.readLine().trim();
                var number = Integer.parseInt(input);
                if (number < 0 || 3 < number) {
                    throw new IllegalArgumentException();
                }
                return number;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат ввода");
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильный выбор");
            }
        }
    }

    public static String getServiceName() {
        String service;
        while (true) {
            try {
                out.write("\nВведите название сервиса: ");
                out.flush();
                service = in.readLine().trim();
                if (service.isBlank()) {
                    throw new IllegalStateException();
                }
                return service;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Пустое название сервиса");
            }
        }
    }

    public static int getNumber() {
        int number;
        while (true) {
            try {
                out.write("Введите ваше число: ");
                out.flush();
                var input = in.readLine().trim();
                number = Integer.parseInt(input);
                if (number < 0) {
                    throw new IllegalStateException();
                }
                return number;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат ввода");
            } catch (IllegalStateException e) {
                System.out.println("Число должно быть больше нуля");
            }
        }
    }

    public static String generate(String service, int number) {
        var generator = new Generator(service, number);
        return generator.generate();
    }
}
