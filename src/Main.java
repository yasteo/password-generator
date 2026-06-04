public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Нет необходимых аргументов");
            System.exit(1);
        }
        String serviceName = args[0];
        int number = 0;
        try {
            number = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неправильное число");
            System.exit(1);
        }
        var gen = new Generator(serviceName, number);
        gen.run();
    }
}
